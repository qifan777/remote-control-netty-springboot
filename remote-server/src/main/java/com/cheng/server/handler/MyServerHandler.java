package com.cheng.server.handler;

import com.cheng.api.handler.MyHandler;
import com.cheng.api.protocol.CommonRequest;
import com.cheng.server.config.RegistryCenter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@ChannelHandler.Sharable
@Slf4j
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    Map<String, MyHandler> handlerMap;
    @Autowired
    RegistryCenter registryCenter;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户:" + ctx.channel().id().asShortText() + "成功连接");
        registryCenter.putUserChannel(ctx.channel().id().asShortText(), ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        CommonRequest commonRequest = (CommonRequest) msg;
        MyHandler serverHandler = handlerMap.get(commonRequest.getCommand());
        if (serverHandler != null) {
            serverHandler.handler(msg, ctx);
            return;
        }
        log.info(commonRequest.getUserId() + "发送给" + commonRequest.getFriendId() + "," + commonRequest.getCommand());
        String friendChannelId = registryCenter.getUserChannelId(commonRequest.getFriendId());
        Channel channel = registryCenter.getUserChannel(friendChannelId);
        if (channel != null) {
            channel.writeAndFlush(msg);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户:" + ctx.channel().id() + "断开连接");
        registryCenter.removeUserChannel(ctx.channel().id().asShortText());
    }
}
