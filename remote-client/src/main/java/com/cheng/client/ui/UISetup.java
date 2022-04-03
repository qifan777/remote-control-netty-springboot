package com.cheng.client.ui;

import com.cheng.client.RemoteClientApplication;
import com.cheng.client.config.UIStopEvent;
import com.cheng.client.ui.view.AbstractView;
import com.cheng.client.ui.view.ControlView;
import com.cheng.client.ui.view.LoginView;
import com.cheng.client.utils.SpringBeanFactory;
import io.netty.channel.nio.NioEventLoopGroup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class UISetup extends Application {
    public static Stage staticStage;
    public static SpringApplication springApplication;
    public static String[] runArgs;

    @Override
    public void init() {

    }

    public static void launch(SpringApplication application, Class<? extends Application> uiApplication, String[] args) {
        runArgs = args;
        springApplication = application;
        launch(uiApplication);
    }

    @Override
    public void start(Stage stage) {
        staticStage = stage;
        ConfigurableApplicationContext applicationContext = springApplication.run(runArgs);
        LoginView bean = applicationContext.getBean(LoginView.class);
        stage.setScene(bean.getScene());
        stage.show();
    }

    @Override
    public void stop() {

    }


}
