---
title: "一个小探针部署教程"
date: 2021-04-29T19:03:06+08:00
---

如你参考本文档编译部署, 这将是一个艰难的过程，首先你得拥有Node，Java， Go的环境。请做好心理准备。

## 1.预览图

![preview.png](https://i.loli.net/2021/04/29/3iM18NoAswOrSGD.png)

## 2.后端部署

为了安全起见, 请先修cabin-backend/src/main/resources/application.yml中的密钥，对了还要修改数据源为你自己的mysql地址

```
cabin:
    # 密钥
    port: 当然你也可以修改端口
    key: 改成你自己的(128bit)
```

然后很简单编译就可以了

```shell
mvn clean && mvn compile && mvn package
```

新生成的target文件下的jar包就是你要的后端文件, 启动即可

```shell
nohup java -jar cabin-backend.jar > nohup.out 2>&1 &
```

## 3.Agent部署

这个编译更简单cabin-agent目录下执行

```shell
CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build main.go
```

然后将config.json和main可执行文件随便复制到被监控机器的任意目录, 修改config.j son中为你自己的信息，启动即可, 如果你和我一样懒,不复制config.json也行首次运行会生成, 生成后还是需要你自己修改

```shell
nohup ./main > nohup.out 2>&1 &
```

## 4.前端部署

前端编译cabin-web目录下执行就行了，然后复制dist中的静态文件到你的nginx中

```shell
npm run build
```

奥对了，不要忘记在nginx配置文件中加这个

```shell
location /cabin-api {
	proxy_pass http://localhost:8088(你的后端地址);
}
```





