package com.cheng.client.ui;

import com.cheng.api.protocol.client.connection.DisConnectRequest;
import com.cheng.api.protocol.client.keyboard.KeyPressRequest;
import com.cheng.api.protocol.client.keyboard.KeyReleaseRequest;
import com.cheng.api.protocol.client.mouse.MouseMoveRequest;
import com.cheng.api.protocol.client.mouse.MousePressRequest;
import com.cheng.api.protocol.client.mouse.MouseReleaseRequest;
import com.cheng.api.protocol.client.mouse.MouseWheelRequest;
import com.cheng.client.config.ClientInfo;
import com.cheng.client.netty.NettyClient;
import com.cheng.client.ui.view.LoginView;
import com.cheng.client.utils.JavaFXToAWTAdaptor;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import java.awt.*;


@Component
@Slf4j
public class ControlController {
    @FXML
    public VBox vBox;
    @FXML
    public ImageView imageView;
    @FXML
    public HBox topHBox;
    @FXML
    public Button hiddenButton;
    @Autowired
    Robot robot;
    @Autowired
    public ClientInfo clientInfo;


    public ControlController() {

    }

    @FXML
    public void initialize() {
        log.info("ControlController初始化");
        imageView.setOnMousePressed(mouseEvent -> {
            log.info("发送单击事件");
            MousePressRequest mousePressRequest = new MousePressRequest();
            mousePressRequest.setBtn(JavaFXToAWTAdaptor.findMouseInAWT(mouseEvent.getButton()));
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
            mouseReleaseRequest.setBtn(JavaFXToAWTAdaptor.findMouseInAWT(mouseEvent.getButton()));
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
        topHBox.setOnKeyPressed(keyEvent -> {
            try {
                //反射获取按键在KeyEvent中对应的编号
                int keyInAWT = JavaFXToAWTAdaptor.findKeyInAWT(keyEvent.getCode());
                if (keyInAWT < 0) return;
                KeyPressRequest keyPressRequest = new KeyPressRequest();
                keyPressRequest.setKeyCode(keyInAWT);
                keyPressRequest.setFriendId(clientInfo.getFriendId());
                keyPressRequest.setUserId(clientInfo.getUserId());
                getClient().channel.writeAndFlush(keyPressRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        topHBox.setOnKeyReleased(keyEvent -> {
            try {
                //反射获取按键在KeyEvent中对应的编号
                int keyInAWT = JavaFXToAWTAdaptor.findKeyInAWT(keyEvent.getCode());
                if (keyInAWT < 0) return;
                KeyReleaseRequest keyReleaseRequest = new KeyReleaseRequest();
                keyReleaseRequest.setKeyCode(keyInAWT);
                keyReleaseRequest.setFriendId(clientInfo.getFriendId());
                keyReleaseRequest.setUserId(clientInfo.getUserId());
                getClient().channel.writeAndFlush(keyReleaseRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    protected void onDisConnectButton() {
        DisConnectRequest disConnectRequest = new DisConnectRequest();
        disConnectRequest.setUserId(clientInfo.getUserId());
        disConnectRequest.setFriendId(clientInfo.getFriendId());
        getClient().channel.writeAndFlush(disConnectRequest);
        //回到登录页面
        log.info(getLoginView().toString());
        UISetup.staticStage.setScene(getLoginView().getScene());
        UISetup.staticStage.show();
    }

    @Lookup
    public NettyClient getClient() {
        return null;
    }

    @Lookup
    public LoginView getLoginView() {
        return null;
    }


}