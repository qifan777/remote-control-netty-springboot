package com.cheng.client.netty.handler.screen;

import com.cheng.api.handler.MyHandler;
import com.cheng.api.protocol.Command;
import com.cheng.api.protocol.screen.ScreenPictureRequest;
import com.cheng.client.ui.ControlController;
import io.netty.channel.ChannelHandlerContext;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service(Command.SCREEN_PICTURE)
public class ScreenPictureHandler implements MyHandler {
    @Autowired
    ControlController controlController;
    @Override
    public void handler(Object params, ChannelHandlerContext handlerContext) {
        ScreenPictureRequest screenRequest = (ScreenPictureRequest) params;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(screenRequest.getImage());
        Image image = new Image(byteArrayInputStream);
        controlController.imageView.setImage(image);
    }
}
