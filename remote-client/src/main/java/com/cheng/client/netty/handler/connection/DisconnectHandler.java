package com.cheng.client.netty.handler.connection;

import com.cheng.api.handler.MyHandler;
import com.cheng.api.protocol.Command;
import com.cheng.client.ui.UISetup;
import com.cheng.client.ui.view.LoginView;
import io.netty.channel.ChannelHandlerContext;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(Command.DISCONNECT)
public class DisconnectHandler implements MyHandler {
    @Autowired
    LoginView loginView;

    @Override
    public void handler(Object params, ChannelHandlerContext handlerContext) {
        //如果是控制端发起断开，则被控制端要停止发送图片
        //如果是被控制端发起断开，则控制端需要回到首页
        ConnectHandler.flag = false;
        UISetup.staticStage.setScene(loginView.getScene());
        UISetup.staticStage.show();
    }
}
