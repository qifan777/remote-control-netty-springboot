package com.cheng.client.netty.handler.mouse;

import com.cheng.api.handler.MyHandler;
import com.cheng.api.protocol.Command;
import com.cheng.api.protocol.client.mouse.MouseMoveRequest;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;


@Service(Command.MOUSE_MOVE)
public class MouseMoveHandler implements MyHandler {
    @Autowired
    Robot robot;

    @Override
    public void handler(Object params, ChannelHandlerContext handlerContext) {
        MouseMoveRequest mouseMoveRequest = (MouseMoveRequest) params;
        robot.mouseMove(mouseMoveRequest.getX().intValue(), mouseMoveRequest.getY().intValue());
    }
}
