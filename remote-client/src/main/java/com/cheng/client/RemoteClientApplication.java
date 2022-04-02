package com.cheng.client;
import com.cheng.client.ui.UISetup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
@Slf4j
public class RemoteClientApplication {


    public static void main(String[] args) {
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(RemoteClientApplication.class);
        SpringApplication application = springApplicationBuilder.headless(false).build();
        UISetup.launch(application, UISetup.class,args);
    }


}
