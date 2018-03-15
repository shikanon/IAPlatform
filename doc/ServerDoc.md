#服务端部署

服务端采用JAVA开发，在部署前可根据配置文件修改数据库入口，集群入口等。


## 一、修改配置文件。
 在启动项目前，需要修改项目《ueSparkMLSys》中的配置文件。包括：数据库配置文件，集群配置文件等。

### 1.1、数据库配置文件

文件路径： **src\main\resources\config.properties**。

该配置文件为Hibernate对于数据库的配置文件，配置主要属性包括
* 数据库链接。（c3p0.url）

* 用户名以及密码。（c3p0.user，c3p0.password）
* C3P0连接池属性。

### 1.2、配置部署环境的host文件。

在《ueSparkMLSys》部署环境的host中添加以下内容。
ps: 如果后续集群增加了新的节点，则同样需要在host文件中增加内容。

```
 192.168.0.245 hadoop-master
 192.168.0.245 mysql
 192.168.0.245 hadoop-slave1
 192.168.0.245 hadoop-slave2
 ```

 