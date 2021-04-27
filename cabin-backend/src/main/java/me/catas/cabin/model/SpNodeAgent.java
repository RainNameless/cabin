package me.catas.cabin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 监控数据实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SpNodeAgent extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long nodeId;

    /**
     * 节点名称
     */
    @TableField(exist = false)
    private String nodeName;

    /**
     * 系统版本
     */
    private String systemOs;

    /**
     * 系统运行时间
     */
    private Long systemUpTime;

    /**
     * CPU版本
     */
    private String cpuVersion;

    /**
     * CPU核心数
     */
    private Long cpuCounts;

    /**
     * CPU使用率
     */
    private String cpuPercent;

    /**
     * 已使用内存
     */
    private Long usedMem;

    /**
     * 总内存
     */
    private Long totalMem;

    /**
     * 可用内存
     */
    private Long availableMem;

    /**
     * 内存使用率
     */
    private String memPercent;

    /**
     * 已使用交换
     */
    private Long usedSwap;

    /**
     * 总交换
     */
    private Long totalSwap;

    /**
     * 空闲交换
     */
    private Long freeSwap;

    /**
     * 交换使用率
     */
    private String swapPercent;

    /**
     * 总硬盘
     */
    private Long totalDisk;

    /**
     * 空闲硬盘
     */
    private Long freeDisk;

    /**
     * 已使用硬盘
     */
    private Long usedDisk;

    /**
     * 发送字节数
     */
    private Long netSendBytes;

    /**
     * 接收字节数
     */
    private Long netRecvBytes;

    /**
     * 每秒发送字节数
     */
    private Long netSendBytesPerSecond;

    /**
     * 每秒接收字节数
     */
    private Long netRecvBytesPerSecond;

}
