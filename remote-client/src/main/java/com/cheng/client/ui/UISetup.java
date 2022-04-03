package com.cheng.client.ui;

import com.cheng.client.ui.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class UISetup extends Application {
    public static Stage staticStage;
    public static SpringApplication springApplication;
    public static String[] runArgs;
    ConfigurableApplicationContext configurableApplicationContext;

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
        this.configurableApplicationContext = applicationContext;
        LoginView bean = applicationContext.getBean(LoginView.class);
        stage.setScene(bean.getScene());
        stage.show();
    }

    @Override
    public void stop() {
        log.info("关闭界面");
        configurableApplicationContext.close();
    }


}
