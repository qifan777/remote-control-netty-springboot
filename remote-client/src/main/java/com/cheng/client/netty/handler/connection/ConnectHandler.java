package com.cheng.client.netty.handler.connection;

import com.cheng.api.handler.MyHandler;
import com.cheng.api.protocol.Command;
import com.cheng.api.protocol.client.connection.ConnectRequest;
import com.cheng.api.protocol.client.screen.ScreenPictureRequest;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

@Service(Command.CONNECT)
@Slf4j
public class ConnectHandler implements MyHandler {
    @Autowired
    Robot robot;
    @Autowired
    ExecutorService executorService;
    public static volatile boolean flag = true;

    @Override
    public void handler(Object params, ChannelHandlerContext handlerContext) {
        ConnectRequest connectRequest = (ConnectRequest) params;
        executorService.submit(() -> {
            flag = true;
            while (flag) {
                try {
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(0, 0, screenSize.width, screenSize.height));
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ImageIO.write(screenCapture, "jpg", byteArrayOutputStream);
                    byte[] bytes = byteArrayOutputStream.toByteArray();
                    ScreenPictureRequest screenRequest = new ScreenPictureRequest();
                    screenRequest.setImage(bytes);
                    screenRequest.setUserId(connectRequest.getFriendId());
                    screenRequest.setFriendId(connectRequest.getUserId());
                    handlerContext.writeAndFlush(screenRequest);
                    Thread.sleep(100);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
