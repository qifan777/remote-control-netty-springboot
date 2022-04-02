package com.cheng.client.ui;

import com.cheng.api.protocol.mouse.*;
import com.cheng.client.RemoteClientApplication;
import com.cheng.client.config.ClientInfo;
import com.cheng.client.netty.NettyClient;
import com.cheng.client.utils.SpringBeanFactory;
import io.netty.channel.Channel;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ControlController {
    @FXML
    public VBox vBox;
    @FXML
    public ImageView imageView;
    @FXML
    public HBox topHBox;
    @Autowired
    Robot robot;
    @Autowired
    public ClientInfo clientInfo;


    Map<MouseButton, Integer> javaFxMouseToRobot = new HashMap<>();

    public ControlController() {
        javaFxMouseToRobot.put(MouseButton.PRIMARY, InputEvent.BUTTON1_DOWN_MASK);
        javaFxMouseToRobot.put(MouseButton.MIDDLE, InputEvent.BUTTON2_DOWN_MASK);
        javaFxMouseToRobot.put(MouseButton.SECONDARY, InputEvent.BUTTON3_DOWN_MASK);
        System.out.println("Control实例化");
    }

    @FXML
    public void initialize() {
        log.info("ControlController初始化");
        imageView.setOnMousePressed(mouseEvent -> {
            log.info("发送单击事件");
            MousePressRequest mousePressRequest = new MousePressRequest();
            mousePressRequest.setBtn(javaFxMouseToRobot.get(mouseEvent.getButton()));
            mousePressRequest.setFriendId(clientInfo.getFriendId());
            mousePressRequest.setUserId(clientInfo.getUserId());
            getClient().channel.writeAndFlush(mousePressRequest);
        });
        imageView.setOnMouseMoved(mouseEvent -> {
            double x = mouseEvent.getPickResult().getIntersectedPoint().getX();
            double y = mouseEvent.getPickResult().getIntersectedPoint().getY();
            Bounds boundsInParent = imageView.getBoundsInParent();
            double height = boundsInParent.getHeight();
            double width = boundsInParent.getWidth();
            double imageWidth = imageView.getImage().getWidth();
            double imageHeight = imageView.getImage().getHeight();
            MouseMoveRequest mouseMoveRequest = new MouseMoveRequest();
            mouseMoveRequest.setUserId(clientInfo.getUserId());
            mouseMoveRequest.setFriendId(clientInfo.getFriendId());
            mouseMoveRequest.setX(x / width * imageWidth);
            mouseMoveRequest.setY(y / height * imageHeight);
            getClient().channel.writeAndFlush(mouseMoveRequest);
        });
        imageView.setOnMouseReleased(mouseEvent -> {
            MouseReleaseRequest mouseReleaseRequest = new MouseReleaseRequest();
            // robot的鼠标定义和javaFx的鼠标定义不一样，需要用适配器模式
            mouseReleaseRequest.setBtn(javaFxMouseToRobot.get(mouseEvent.getButton()));
            mouseReleaseRequest.setFriendId(clientInfo.getFriendId());
            mouseReleaseRequest.setUserId(clientInfo.getUserId());
            getClient().channel.writeAndFlush(mouseReleaseRequest);
        });
        imageView.setOnScroll(scrollEvent -> {
            System.out.println(scrollEvent.toString());
            MouseWheelRequest mouseWheelRequest = new MouseWheelRequest();
            //deltaY大于0则是代表向上 小于则是向下
            mouseWheelRequest.setDirection(scrollEvent.getDeltaY() < 0);
            mouseWheelRequest.setFriendId(clientInfo.getFriendId());
            mouseWheelRequest.setUserId(clientInfo.getUserId());
            getClient().channel.writeAndFlush(mouseWheelRequest);
        });
    }

    @FXML
    protected void onHelloButtonClick() throws IOException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(0, 0, screenSize.width, screenSize.height));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(screenCapture, "jpg", byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        Image image = new Image(byteArrayInputStream);
        imageView.setImage(image);
    }

    @Lookup
    public NettyClient getClient() {
        return null;
    }


}