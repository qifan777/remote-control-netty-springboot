package com.cheng.client.netty.handler;

import com.cheng.api.handler.MyHandler;
import com.cheng.api.protocol.CommonRequest;
import com.cheng.api.protocol.registry.RegistryRequest;
import com.cheng.client.config.ClientInfo;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.SocketAddress;
import java.util.Map;

@Slf4j
@Service
@ChannelHandler.Sharable
public class MyClientHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    Map<String, MyHandler> handlers;
    @Autowired
    ClientInfo clientInfo;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        SocketAddress socketAddress = ctx.channel().localAddress();
        log.info("连接成功:" + socketAddress.toString());
        RegistryRequest registryRequest = new RegistryRequest();
        registryRequest.setUserId(clientInfo.getUserId());
        ctx.writeAndFlush(registryRequest);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        CommonRequest commonRequest = (CommonRequest) msg;
        MyHandler myHandler = handlers.get(commonRequest.getCommand());
        log.info("收到消息" + commonRequest.getCommand());
        if (myHandler != null) {
            myHandler.handler(msg, ctx);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage());
    }
}
