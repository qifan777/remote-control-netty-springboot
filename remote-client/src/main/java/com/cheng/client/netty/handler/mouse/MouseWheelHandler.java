package com.cheng.client.netty.handler.mouse;

import com.cheng.api.handler.MyHandler;
import com.cheng.api.protocol.Command;
import com.cheng.api.protocol.client.mouse.MouseWheelRequest;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service(Command.MOUSE_WHEEL)
@Slf4j
public class MouseWheelHandler implements MyHandler {
    @Autowired
    Robot robot;

    @Override
    public void handler(Object params, ChannelHandlerContext handlerContext) {
        MouseWheelRequest mouseWheelRequest = (MouseWheelRequest) params;
        if (mouseWheelRequest.getDirection()) {
            robot.mouseWheel(3);
        } else {
            robot.mouseWheel(-3);
        }
    }
}
