package main

import (
	"cabin-agent/model"
	"cabin-agent/service"
	"cabin-agent/utils"
	"encoding/json"
	"fmt"
	"io"
	"log"
	"net"
	"os"
	"strconv"
)

// 日志输出文件
func init() {
	logFile, err := os.OpenFile("cabin_agent.log", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0766)
	if err != nil {
		panic(err)
	}
	// 将log同时输入在控制台和文件
	mw := io.MultiWriter(os.Stdout, logFile)
	log.SetOutput(mw) // 将文件设置为log输出的文件
	log.SetPrefix("[Cabin Agent Log]")
	log.SetFlags(log.LstdFlags | log.Lshortfile)
	return
}

// 配置文件校验
func checkParams(config model.Config) {
	serverStr := net.ParseIP(config.Server)
	if serverStr == nil {
		panic("IP地址不正确")
	}
	numPort, err := strconv.Atoi(config.Port)
	if err != nil || (numPort < 0 || numPort > 65535) {
		panic("端口不正确")
	}
	if config.NodeId == "" || config.ClientKey == "" {
		panic("节点ID或客户端KEY不能为空")
	}
}

func main() {
	// 读取配置文件
	exists, _ := utils.Exists("config.json")
	if exists {
		file, _ := os.Open("config.json")
		// 如果存在
		defer func(file *os.File) {
			err := file.Close()
			if err != nil {
				log.Println("关闭文件流失败")
				panic(err)
			}
		}(file)
		decoder := json.NewDecoder(file)
		config := model.Config{}
		err := decoder.Decode(&config)
		if err != nil {
			fmt.Println("序列化配置文件错误:", err)
			panic(err)
		}
		// 参数校验(错误直接会panic)
		checkParams(config)
		// 开启Agent线程
		service.Start(config)
	} else {
		file, _ := os.OpenFile("config.json", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0766)
		// 如果不存在
		log.Println("配置文件不存在, 已创建, 请打开config.json修改启动参数")
		var config model.Config
		jsonConfig, _ := json.Marshal(config)
		_, err := file.Write(jsonConfig)
		if err != nil {
			log.Println("创建配置文件失败", err)
			panic(err)
		}
		return
	}
}
