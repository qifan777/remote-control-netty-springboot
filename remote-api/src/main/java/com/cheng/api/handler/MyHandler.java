package com.cheng.api.handler;

import io.netty.channel.ChannelHandlerContext;

public interface MyHandler {
    void handler(Object params, ChannelHandlerContext handlerContext);
}
