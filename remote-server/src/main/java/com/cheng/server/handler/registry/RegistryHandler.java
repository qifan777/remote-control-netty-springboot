package com.cheng.server.handler.registry;

import com.cheng.api.handler.MyHandler;
import com.cheng.api.protocol.Command;
import com.cheng.api.protocol.registry.RegistryRequest;
import com.cheng.server.config.RegistryCenter;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(Command.REGISTRY)
public class RegistryHandler implements MyHandler {
    @Autowired
    RegistryCenter registryCenter;

    @Override
    public void handler(Object params, ChannelHandlerContext handlerContext) {
        RegistryRequest registry = (RegistryRequest) params;
        registryCenter.putUserIdChannelId(registry.getUserId(), handlerContext.channel().id().asShortText());
        System.out.println(registry.getUserId() + "注册成功");
    }
}
