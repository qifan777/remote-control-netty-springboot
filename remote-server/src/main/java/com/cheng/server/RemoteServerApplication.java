package com.cheng.server;

import com.cheng.api.protocol.registry.RegistryRequest;
import com.cheng.server.handler.MyChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
@Slf4j
public class RemoteServerApplication implements CommandLineRunner {
    @Value("${remote.server.port}")
    Integer port;
    Channel channel;
    @Autowired
    MyChannelInitializer channelInitializer;
    private final EventLoopGroup parentGroup = new NioEventLoopGroup(2);
    private final EventLoopGroup childGroup = new NioEventLoopGroup();

    public static void main(String[] args) {
        SpringApplication.run(RemoteServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        bind();
    }

    public void bind() {
        ChannelFuture future = null;

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(channelInitializer);
            future = bootstrap.bind(new InetSocketAddress(port)).syncUninterruptibly();
            this.channel = future.channel();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != future && future.isSuccess()) {
                log.info("远程控制服务端启动成功。");
            } else {
                log.error("远程控制服务端启动失败。");
            }
        }
    }

    public void destroy() {
        if (null == channel) return;
        channel.close();
        parentGroup.shutdownGracefully();
        childGroup.shutdownGracefully();
    }

    public Channel channel() {
        return channel;
    }


}
