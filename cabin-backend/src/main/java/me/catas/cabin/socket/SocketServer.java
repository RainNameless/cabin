package me.catas.cabin.socket;

import lombok.extern.slf4j.Slf4j;
import me.catas.cabin.mapper.SpNodeAgentMapper;
import me.catas.cabin.model.SpNodeAgent;
import me.catas.cabin.utils.AESUtil;
import me.catas.cabin.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class SocketServer implements ApplicationRunner {

    @Resource(name = "defaultThreadPool")
    private ThreadPoolTaskExecutor executor;

    @Value("${cabin.agent-server.key}")
    private String agentServerKey;

    @Resource
    private SpNodeAgentMapper spNodeAgentMapper;

    //这是一个不断等待获取网络传入请求的服务端Socket
    private ServerSocket serverSocket;

    /**
     * 构造方法
     */
    public SocketServer(@Value("${cabin.agent-server.port:6666}") Integer agentServerPort) {
        try {
            serverSocket = new ServerSocket(agentServerPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 无限循环的监听客户端的连接
     * Listens for a connection to be made to this socket and accepts it.
     * 当有一个连接产生，将结束accept()方法的阻塞
     * The method blocks until a connection is made.
     */
    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run(ApplicationArguments args) {
        // 另起线程开启Socket监听, 防止阻塞主线程
        executor.execute(() -> {
            if (StringUtils.isBlank(agentServerKey)) {
                log.error("监控服务端启动失败, 密钥未配置");
                return;
            }
            while (true) {
                try {
                    // 阻塞监听
                    Socket clientConnectSocket = serverSocket.accept();
                    log.info("客户端已连接");
                    executor.execute(() -> {
                        try {
                            // 读取客户端传过来信息的DataInputStream
                            InputStream inputStream = clientConnectSocket.getInputStream();
                            // 向客户端发送信息的DataOutputStream
                            OutputStream outputStream = clientConnectSocket.getOutputStream();
                            boolean isClose;
                            listen:
                            while (true) {
                                // 判断是否断开
                                isClose = isServerClose(clientConnectSocket);
                                // 连接存活开始读数据
                                if (!isClose) {
                                    byte[] data = new byte[2048];
                                    int len;
                                    while ((len = inputStream.read(data)) != -1) {
                                        String message = new String(data, 0, len);
                                        try {
                                            String agentMsg = AESUtil.decrypt(message, agentServerKey);
                                            SpNodeAgent nodeAgent = JsonUtil.toJavaObject(agentMsg, SpNodeAgent.class);
                                            // 这里使用mysql保存数据
                                            spNodeAgentMapper.insert(nodeAgent);
                                        } catch (Exception e) {
                                            log.warn("客户端密钥不正确, 已强制离线");
                                            outputStream.write("客户端密钥不正确, 请检查配置文件"
                                                    .getBytes(StandardCharsets.UTF_8));
                                            // 数据解密异常, 关闭监听线程
                                            break listen;
                                        }
                                        outputStream.write("Success".getBytes(StandardCharsets.UTF_8));
                                    }
                                } else {
                                    log.warn("客户端已离线");
                                    // 离线关闭线程
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            log.error("Socket连接异常", e);
                        } finally {
                            try {
                                clientConnectSocket.close();
                            } catch (IOException e) {
                                log.error("连接关闭异常");
                            }
                        }
                    });
                } catch (Exception e) {
                    log.error("服务端监听连接程序异常", e);
                }
            }
        });
    }

    /**
     * 判断客户端是否断开
     * 断开返回true,没有返回false
     *
     * @param socket socket
     * @return java.lang.Boolean
     * @author Calisto
     * @date 2021/4/23
     **/
    public Boolean isServerClose(Socket socket) {
        try {
            //发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
            socket.sendUrgentData(0xFF);
            return false;
        } catch (Exception se) {
            return true;
        }
    }
}
