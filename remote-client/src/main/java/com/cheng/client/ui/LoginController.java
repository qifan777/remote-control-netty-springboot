package com.cheng.client.ui;

import com.cheng.api.protocol.client.connection.ConnectRequest;
import com.cheng.client.config.ClientInfo;
import com.cheng.client.netty.NettyClient;
import com.cheng.client.ui.view.ControlView;
import io.netty.channel.Channel;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoginController {
    @FXML
    Button connectButton;
    @FXML
    TextField textField;
    @Autowired
    public ClientInfo clientInfo;


    public LoginController() {
    }

    @FXML
    public void initialize() {
        log.info("loginController初始化");
    }

    public void connect() {
        NettyClient client = getClient();
        Channel channel = client.channel;
        log.info("连接" + textField.getCharacters().toString());
        clientInfo.setFriendId(textField.getCharacters().toString());
        ConnectRequest connectRequest = new ConnectRequest();
        connectRequest.setFriendId(clientInfo.getFriendId());
        connectRequest.setUserId(clientInfo.getUserId());
        channel.writeAndFlush(connectRequest);
        UISetup.staticStage.setScene(getControlView().getScene());
    }

    @Lookup
    public NettyClient getClient() {
        return null;
    }

    @Lookup
    public ControlView getControlView() {
        return null;
    }
}
