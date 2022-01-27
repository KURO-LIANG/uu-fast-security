slowcom-smart-community
=============


### 系统架构图
![Alt text](http://assets.processon.com/chart_image/60b9c24a7d9c086753adbd5f.png)


### 组织结构
>```
>slowcom-smart-community
>├── slowcom-register
>├── slowcom-config
>├── slowcom-gateway
>├── slowcom-monitor
>├── slowcom-core
>├── slowcom-interface
>├── slowcom-utils
>├── slowcom-server
>   ├── slowcom-admin
>   ├── slowcom-user
>   ├── slowcom-devices
>   └── slowcom-family
>```



### 服务结构
#### ·注册中心：slowcom-register(端口：7100)
#### ·配置中心：slowcom-config(端口：7103)
#### ·网关服务：slowcom-gateway(端口：7101)
#### ·监控服务：slowcom-monitor(端口：7102)
#### ·后台管理服务：slowcom-admin(端口：7201)
#### ·用户统一体系服务：slowcom-user(端口：7202)
#### ·设备服务：slowcom-devices(端口：7203)
#### ·家庭服务：slowcom-family(端口：7204)


### 服务启动顺序
#### 注册中心 -> 配置中心 -> 网关服务 -> 监控服务 -> 后台管理服务/用户服务/设备服务/家庭服务





### 接口文档入口
#### http://localhost:7101/doc.html（本地）
#### 网关已经整合knife4j，可从网关直接切换各个服务查看对应接口
