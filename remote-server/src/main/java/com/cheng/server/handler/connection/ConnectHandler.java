package com.cheng.server.handler.connection;

import com.cheng.api.handler.MyHandler;
import com.cheng.api.protocol.Command;
import com.cheng.api.protocol.CommonRequest;
import com.cheng.api.protocol.server.connection.ConnectResponse;
import com.cheng.server.config.RegistryCenter;
import com.cheng.server.handler.DefaultHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(Command.CONNECT)
@Slf4j
public class ConnectHandler implements MyHandler {
    @Autowired
    RegistryCenter registryCenter;
    @Autowired
    DefaultHandler defaultHandler;

    @Override
    public void handler(Object params, ChannelHandlerContext handlerContext) {
        CommonRequest request = (CommonRequest) params;
        ConnectResponse connectResponse = new ConnectResponse();
        connectResponse.setRequestId(request.getRequestId());
        connectResponse.setUserId(request.getUserId());
        Channel channel = registryCenter.getUserChannelFromUserId(request.getFriendId());
        //如果对方的连接存在则响应连接成功
        connectResponse.setIsSuccess(channel != null);
        handlerContext.channel().writeAndFlush(connectResponse);
        //将消息转发
        defaultHandler.handler(params, handlerContext);
    }
}
