package com.cheng.client.config;

import org.springframework.context.ApplicationEvent;

public class UIStopEvent extends ApplicationEvent {
    public UIStopEvent(Object source) {
        super(source);
    }
}
