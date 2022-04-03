package com.cheng.client;

import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) throws IllegalAccessException {

        Field[] fields = KeyEvent.class.getFields();
        List<Field> f1 = Arrays.stream(fields).filter(new Predicate<Field>() {
            @Override
            public boolean test(Field field) {
                String name = field.getName().toLowerCase().replace("vk_", "").replace("_", "");
                return name.equals(KeyCode.CONTROL.toString().toLowerCase());
            }
        }).collect(Collectors.toList());
        System.out.println(f1.size());
        if (f1.size() > 0) {
            Class<KeyEvent> keyEventClass = KeyEvent.class;
            System.out.println(f1.get(0).getInt(keyEventClass));
        }
    }
}
