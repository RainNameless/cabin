package utils

import (
	"cabin-agent/model"
	"fmt"
	"github.com/shirou/gopsutil/v3/cpu"
	"github.com/shirou/gopsutil/v3/disk"
	"github.com/shirou/gopsutil/v3/host"
	"github.com/shirou/gopsutil/v3/mem"
	"github.com/shirou/gopsutil/v3/net"
	"time"
)

// CollectInfo 收集系统信息 /**
func CollectInfo(result *model.Result) {
	// 获取本机信息
	serverInfo, _ := host.Info()
	result.SystemOS = serverInfo.OS
	result.SystemUptime = serverInfo.Uptime
	// 获取CPU版本与核心数
	cpuInfos, _ := cpu.Info()
	if len(cpuInfos) > 0 {
		cpuInfo := cpuInfos[0]
		result.CpuCounts = cpuInfo.Cores
		result.CpuVersion = cpuInfo.ModelName
	}
	// 获取cpu使用率
	cpuPercent, _ := cpu.Percent(time.Second, false)
	if len(cpuPercent) > 0 {
		result.CpuPercent = fmt.Sprintf("%.2f", cpuPercent[0])
	}
	// 获取内存使用情况
	memInfo, _ := mem.VirtualMemory()
	result.TotalMem = memInfo.Total
	result.UsedMem = memInfo.Used
	result.AvailableMem = memInfo.Available
	result.MemPercent = fmt.Sprintf("%.2f", memInfo.UsedPercent)
	// 获取交换内存使用情况
	swapInfo, _ := mem.SwapMemory()
	result.TotalSwap = swapInfo.Total
	result.UsedSwap = swapInfo.Used
	result.FreeSwap = swapInfo.Free
	result.SwapPercent = fmt.Sprintf("%.2f", swapInfo.UsedPercent)
	// 获取磁盘使用情况
	diskInfo, _ := disk.Partitions(true)
	var totalDisk uint64
	var freeDisk uint64
	var usedDisk uint64
	for _, value := range diskInfo {
		usage, _ := disk.Usage(value.Mountpoint)
		totalDisk += usage.Total
		freeDisk += usage.Free
		usedDisk += usage.Used
	}
	result.TotalDisk = totalDisk
	result.FreeDisk = freeDisk
	result.UsedDisk = usedDisk
	// 获取网络使用情况
	preNetCounters, _ := net.IOCounters(false)
	time.Sleep(1 * time.Second)
	nextCounters, _ := net.IOCounters(false)
	if len(preNetCounters) > 0 && len(nextCounters) > 0 {
		preCount := preNetCounters[0]
		nextCount := nextCounters[0]
		// 计算网速
		result.NetSendBytesPerSecond = nextCount.BytesSent - preCount.BytesSent
		result.NetRecvBytesPerSecond = nextCount.BytesRecv - preCount.BytesRecv
		// 统计使用流量
		result.NetSendBytes = nextCount.BytesSent
		result.NetRecvBytes = nextCount.BytesRecv
	}
}
