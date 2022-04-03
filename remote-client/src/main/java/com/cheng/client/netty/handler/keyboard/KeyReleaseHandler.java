package com.cheng.client.netty.handler.keyboard;

import com.cheng.api.handler.MyHandler;
import com.cheng.api.protocol.Command;
import com.cheng.api.protocol.client.keyboard.KeyReleaseRequest;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service(Command.KEY_RELEASE)
public class KeyReleaseHandler implements MyHandler {
    @Autowired
    Robot robot;

    @Override
    public void handler(Object params, ChannelHandlerContext handlerContext) {
        KeyReleaseRequest releaseRequest = (KeyReleaseRequest) params;
        robot.keyRelease(releaseRequest.getKeyCode());
    }
}
