package model

type Result struct {
	// 节点名称
	NodeName string `json:"nodeName"`
	// 系统版本
	SystemOS string `json:"systemOs"`
	// 系统运行时间(秒)
	SystemUptime uint64 `json:"systemUpTime"`
	// CPU型号
	CpuVersion string `json:"cpuVersion"`
	// CPU核心数(逻辑)
	CpuCounts int32 `json:"cpuCounts"`
	// CPU使用率
	CpuPercent string `json:"cpuPercent"`
	// 已使用内存
	UsedMem uint64 `json:"usedMem"`
	// 总内存
	TotalMem uint64 `json:"totalMem"`
	// 可用内存
	AvailableMem uint64 `json:"availableMem"`
	// 内存使用率
	MemPercent string `json:"memPercent"`
	// 已使用交换内存
	UsedSwap uint64 `json:"usedSwap"`
	// 总交换内存
	TotalSwap uint64 `json:"totalSwap"`
	// 空闲交换内存
	FreeSwap uint64 `json:"freeSwap"`
	// 交换内存使用率
	SwapPercent string `json:"swapPercent"`
	// 总硬盘
	TotalDisk uint64 `json:"totalDisk"`
	// 空闲硬盘
	FreeDisk uint64 `json:"freeDisk"`
	// 已使用硬盘
	UsedDisk uint64 `json:"usedDisk"`
	// 发送字节数
	NetSendBytes uint64 `json:"netSendBytes"`
	// 接收字节数
	NetRecvBytes uint64 `json:"netRecvBytes"`
	// 每秒发送字节数
	NetSendBytesPerSecond uint64 `json:"netSendBytesPerSecond"`
	// 每秒接收字节数
	NetRecvBytesPerSecond uint64 `json:"netRecvBytesPerSecond"`
	// 代理状态
	ProxyStatus string `json:"proxyStatus"`
	// 代理请求数量
	ProxyReqs int `json:"proxyReqs"`
	// 代理平均服务速度(单位秒)
	ProxyServiceTime string `json:"proxyServiceTime"`
}
