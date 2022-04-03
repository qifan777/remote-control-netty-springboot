package com.cheng.client.netty;

import com.cheng.api.protocol.client.connection.DisConnectRequest;
import com.cheng.client.config.ClientInfo;
import com.cheng.client.netty.handler.MyChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
@Slf4j
public class NettyClient implements CommandLineRunner, DisposableBean {
    @Autowired
    MyChannelInitializer channelInitializer;
    @Autowired
    public EventLoopGroup workGroup;

    @Autowired
    ClientInfo clientInfo;
    @Autowired
    ExecutorService executorService;

    public Channel channel;

    public void connect(String inetHost, int inetPort) {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ, true)
                    .handler(channelInitializer);
            ChannelFuture future = bootstrap.connect(inetHost, inetPort).sync();
            channel = future.channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        executorService.submit(() -> connect(clientInfo.getHost(), clientInfo.getPort()));

    }


    @Override
    public void destroy() {
        log.info("关闭netty");
        DisConnectRequest disConnectRequest = new DisConnectRequest();
        disConnectRequest.setUserId(clientInfo.getUserId());
        disConnectRequest.setFriendId(clientInfo.getFriendId());
        channel.writeAndFlush(disConnectRequest);
        executorService.shutdown();
        workGroup.shutdownGracefully();
    }
}
