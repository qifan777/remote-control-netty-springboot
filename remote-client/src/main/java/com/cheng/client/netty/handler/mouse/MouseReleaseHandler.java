package com.cheng.client.netty.handler.mouse;

import com.cheng.api.protocol.Command;
import com.cheng.api.handler.MyHandler;
import com.cheng.api.protocol.mouse.MouseReleaseRequest;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service(Command.MOUSE_RELEASE)
public class MouseReleaseHandler implements MyHandler {
    @Autowired
    Robot robot;

    @Override
    public void handler(Object params, ChannelHandlerContext handlerContext) {
        MouseReleaseRequest releaseRequest = (MouseReleaseRequest) params;
        robot.mouseRelease(releaseRequest.getBtn());
    }
}
