# 部署集群环境

  集群底层环境采用Docker容器支撑，所以在部署环境前，需要在系统中配置Docker环境。 

ps:docker版本建议采用1.12.*以上版本。

## 2.1、配置docker环境。

* 安装docker

 `yum install -y docker-engine`

 * 启动docker服务

 `systemctl start docker.service`

* 查看docker版本

 `docker version`

* 设置开机自动启动服务

`sudo systemctl enable docker`


## 2.2、拉取镜像文件。

##  2.2.1、获取mysql镜像文件。

* 从[docker hub](https://hub.docker.com/u/nkxujun/)上获取镜像文件。

`docker pull nkxujun/mysql_eml:version (latest default)`

 

## 2.2.2、获取ubuntu_eml镜像。

* 集群的镜像是基于ubuntu的镜像进行开发的。所以得先拉取该镜像。

`docker pull nkxujun/ubuntu_eml:version(latest default)`


## 2.2.3、构建集群镜像。

镜像相关文件路径： 192.168.0.245:/home/sigma/EMLspark2.0.1/EML/Environment/

* 执行文件 build.sh

`sh build.sh`   

build.sh：使用同目录下的 “cluster"文件夹中的dockerfile来构建镜像。 
镜像中包括了集群的默认配置，包括Hadoop的配置文件，Spark配置文件，Oozie配置文件。

## 2.2.4、为docker集群创建一个网桥

创建名称为shadownet，ip网段为172.18.0.0的网桥。

`docker network create --subnet=172.18.0.0/16 shadownet`

可使用`docker network ls` 来查看是否创建成功。

## 2.2.5 创建集群需要使用的容器

执行文件run_containers.sh

`sh run_containers.sh`


### 创建的容器包括：

* mysql容器

  ip地址：172.18.0.2 

* hadoop-master节点

  ip地址：172.18.0.3

* hadoop-slave1节点

  ip地址：172.18.0.4

* hadoop-slave2节点

  ip地址：172.18.0.5

ps:在run_containers.sh中默认创建2个slave节点，1个master节点。如果需要动态增加节点。可参考后面章节。


## 2.2.6 确定容器的网络连通性

因为hadoop集群节点中的通信依赖于ssh，所以在启动集群前，先验证下各个节点间的联通性。


* 使用 `docker exec -it hadoop-master /bin/bash` 进入hadoop-master节点。

* 在hadoop-master节点中，使用 `ssh localhost`、`ssh hadoop-slave1`、'ssh hadoop-slave2'来检验各个节点的网络通讯。

* 如果通讯失败。则进入失败节点中启动ssh服务。`/etc/init.d/ssh start`

* 当测试时出现错误代码 `Bad owner or permissions on ~/.ssh/config.`，则给予ssh权限。`chmod 600 ~/.ssh/config`



## 2.2.7 初始化mysql数据库

初始化Oozie和ueSparkMLSys所需的数据表格。

`sh init_mysql.sh`


## 2.2.8 启动spark，HDFS和Yarn服务

## 启动hadoop和Spark服务

* 进入hadoop-master节点。

`docker exec -it hadoop-master bash`

* 启动spark和HDFS服务

`sh /root/start-hadoop.sh`

* 检验hadoop服务是否启动成功。

`sh run-wordcount.sh`

* 打开网址“ http://hadoop-master:50070/” 查看HDFS集群信息。 ps:hadoop-master为集群的ip（192.168.0.245）


## 启动oozie服务

* 进入hadoop-master节点。

`docker exec -it hadoop-master bash`

* 启动oozie服务

`sh /root/start-oozie.sh`

* 打开网址 “http://hadoop-master:11000/oozie/” 检验oozie服务是否启动成功
