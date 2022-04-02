package com.cheng.client.config;

import com.cheng.client.ui.view.AbstractView;
import com.cheng.client.utils.SpringBeanFactory;
import javafx.fxml.FXMLLoader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ViewPostProcessor implements BeanPostProcessor, ApplicationContextAware {
    ApplicationContext applicationContext;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.toLowerCase().endsWith("view")) {
            Class<?> beanClass = bean.getClass();
            String fxmlName = beanClass.getSimpleName().toLowerCase().replace("view", "") + ".fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(beanClass.getResource(fxmlName));
            fxmlLoader.setControllerFactory(this::controllerFactory);
            try {
                AbstractView abstractView = (AbstractView) bean;
                abstractView.setXmlView(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bean;

    }


    public Object controllerFactory(Class<?> controlType) {
        return this.applicationContext.getBean(controlType);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
