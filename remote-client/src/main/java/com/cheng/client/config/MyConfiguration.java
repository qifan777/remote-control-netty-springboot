package com.cheng.client.config;

import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class MyConfiguration {
    @Bean
    public Robot robot() throws AWTException {
        return new Robot();
    }

    @Bean
    public ExecutorService executorService() {
        return new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

    @Bean
    public NioEventLoopGroup eventLoopGroup() {
        return new NioEventLoopGroup();
    }
}
