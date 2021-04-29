package service

import (
	"cabin-agent/model"
	"cabin-agent/utils"
	"encoding/json"
	"errors"
	"fmt"
	"io"
	"log"
	"net"
	"os"
	"syscall"
	"time"
)

var errUnexpectedRead = errors.New("unexpected read from socket")

// Start 开启Agent线程
func Start(config model.Config) {
	server := fmt.Sprintf("%s:%s", config.Server, config.Port)
	tcpAddr, err := net.ResolveTCPAddr("tcp4", server)
	if err != nil {
		_, _ = fmt.Fprintf(os.Stderr, "Fatal error: %s", err.Error())
		os.Exit(1)
	}
	conn, err := net.DialTCP("tcp", nil, tcpAddr)
	if err != nil {
		_, _ = fmt.Fprintf(os.Stderr, "Fatal error: %s", err.Error())
		os.Exit(1)
	}
	log.Println("连接建立成功")
	defer func(conn *net.TCPConn) {
		err := conn.Close()
		if err != nil {
			log.Println("关闭连接失败")
		}
	}(conn)
	for {
		var retryNum = 1
		err := connCheck(conn)
		if err != nil {
			log.Println("连接已断开")
			for {
				// 每5秒尝试重连一次
				time.Sleep(5 * time.Second)
				log.Print("自动重连中, 尝试第", retryNum, "次")
				retryNum += 1
				retryConn, err := net.DialTCP("tcp", nil, tcpAddr)
				if err != nil {
					continue
				} else {
					// 重连成功
					log.Print("自动重连成功")
					conn = retryConn
					break
				}
			}
		} else {
			var result model.Result
			// 收集系统信息
			utils.CollectInfo(&result)
			// 设置节点ID
			result.NodeName = config.NodeName
			// 序列化为JSON
			jsonResult, _ := json.Marshal(result)
			// 发送AES加密后的数据
			encrypt, _ := utils.Encrypt(jsonResult, config.ClientKey)
			sender(conn, encrypt)
		}
	}
}

//发送信息
func sender(conn net.Conn, message string) {
	_, _ = conn.Write([]byte(message))
	//接收服务端反馈
	buffer := make([]byte, 2048)
	n, err := conn.Read(buffer)
	if err != nil {
		log.Println(conn.RemoteAddr().String(), "读取服务端反馈错误: ", err)
		return
	}
	log.Println(conn.RemoteAddr().String(), "服务端反馈信息: ", string(buffer[:n]))
}

//连接存活检查
func connCheck(conn net.Conn) error {
	var sysErr error
	sysConn, ok := conn.(syscall.Conn)
	if !ok {
		return nil
	}
	rawConn, err := sysConn.SyscallConn()
	if err != nil {
		return err
	}
	err = rawConn.Read(func(fd uintptr) bool {
		var buf [1]byte
		n, err := syscall.Read(int(fd), buf[:])
		switch {
		case n == 0 && err == nil:
			sysErr = io.EOF
		case n > 0:
			sysErr = errUnexpectedRead
		case err == syscall.EAGAIN || err == syscall.EWOULDBLOCK:
			sysErr = nil
		default:
			sysErr = err
		}
		return true
	})
	if err != nil {
		return err
	}
	return sysErr
}
