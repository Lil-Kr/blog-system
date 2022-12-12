# 博客系统项目信息

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

- [博客系统项目信息](#博客系统项目信息)
  - [项目开发版本说明](#项目开发版本说明)
    - [前端架构](#前端架构)
    - [后端架构](#后端架构)
  - [后端服务端口规划](#后端服务端口规划)
    - [服务器软件安装端口规划](#服务器软件安装端口规划)
    - [应用程序端口规划](#应用程序端口规划)

<!-- /code_chunk_output -->

## 项目开发版本说明

### 前端架构

技术 | 版本 | 官网链接
---------|----------|---------
 reactjs | v18.x | https://reactjs.org/
 typescript | v4.x | xxx 
 router | v6.x | xxx
 redux | v8.x | xxx
 antd | v4.24.x | https://4x.ant.design/
 nodejs | v16.15.0 | https://nodejs.org/en/
 nvm | v1.1.9 | https://github.com/coreybutler/nvm-windows
 vite | v2.0 | https://vitejs.dev/guide/

### 后端架构

技术 | 版本 | 官网链接
---------|----------|---------
spring-boot | 2.6.11 | https://spring.io/projects/spring-boot#learn
spring-cloud-alibaba | xxx | xxx
nacos-server | 2.0.4 | https://github.com/alibaba/nacos/releases/tag/2.0.4
mybatis-plus | xxx | xxx
MySQL | v8.x | xxx
redis | xxx |xxx
Spring Security | xxx | xxx
ElasticSearch | xxx | xxx

----

## 后端服务端口规划

### 服务器软件安装端口规划


所需软件 | 端口分配 | 说明
---------|----------|---------
 Nacos | B1 | C1
 spring-cloud-java code | B2 | C2
 nginx | B3 | C3
 MySQL | B3 | C3
 Redis | B3 | C3
 ES | B3 | C3

### 应用程序端口规划

服务名 | 端口分配 | 说明 | 集群实例数量
---------|----------|---------|---------
 gateway-server | 8010 | 网官 | 1
 nacos-client | 8020 | 服务注册发现, 配置中心 | 1
 blog-admin | 8030 | 对外提供后台管理的所有api | 1
 blog-web | 8040 | 对外提供web端的所有api | 1
 auth-server | 8050 | 权限服务 | 1
 user-server | 8060 | 用户服务 | 1
 blog-server | 8070 | 博客服务 | 1
 picture-server | 8081 | 图片服务 | 1
 search-server | 8090 | 搜索服务 | 1
 monitor-server | 9010 | 监控服务 | 1
 sys-server | 9020 | 系统服务 | 1
