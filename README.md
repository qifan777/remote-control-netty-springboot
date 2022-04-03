# 起凡远程操控软件
技术栈: netty+springboot+javaFX

### 运行步骤
1. ``git clone https://github.com/jarcheng/remote-control-netty-springboot.git``
2. 导入idea,在idea右侧maven中的remote-control模块运行install
3. 运行服务端 在remote-server/target里面找到打包好的jar包 java -jar remote-server-0.0.1-SNAPSHOT.jar
4. 运行客户端 在remote-client/target里面找到打包好的jar包 java -jar remote-client-0.0.1-SNAPSHOT.jar --remote.server.host=服务端ip
