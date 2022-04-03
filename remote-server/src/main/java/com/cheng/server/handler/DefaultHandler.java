package com.cheng.server.handler;

import com.cheng.api.handler.MyHandler;
import com.cheng.api.protocol.CommonRequest;
import com.cheng.server.config.RegistryCenter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultHandler implements MyHandler {
    @Autowired
    RegistryCenter registryCenter;

    @Override
    public void handler(Object msg, ChannelHandlerContext handlerContext) {
        CommonRequest commonRequest = (CommonRequest) msg;
        log.info(commonRequest.getUserId() + "发送给" + commonRequest.getFriendId() + "," + commonRequest.getCommand());
        Channel channel = registryCenter.getUserChannelFromUserId(commonRequest.getFriendId());
        if (channel != null) {
            channel.writeAndFlush(msg);
        }
    }
}
