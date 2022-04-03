package com.cheng.client.utils;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaFXToAWTAdaptor {
    public static Field[] fields = KeyEvent.class.getFields();
    public static Map<MouseButton, Integer> javaFxMouseToRobot = new HashMap<>();

    static {
        javaFxMouseToRobot.put(MouseButton.PRIMARY, InputEvent.BUTTON1_DOWN_MASK);
        javaFxMouseToRobot.put(MouseButton.MIDDLE, InputEvent.BUTTON2_DOWN_MASK);
        javaFxMouseToRobot.put(MouseButton.SECONDARY, InputEvent.BUTTON3_DOWN_MASK);
    }

    public static int findKeyInAWT(KeyCode javaFXKeyCode) throws Exception {
        List<Field> f1 = Arrays.stream(fields).filter(field -> {
            String name = field.getName().toLowerCase().replace("vk", "").replace("_", "");
            return name.equals(javaFXKeyCode.getName().toLowerCase()) || name.equals(javaFXKeyCode.toString().toLowerCase());
        }).collect(Collectors.toList());
        if (f1.size() > 0) {
            Class<KeyEvent> keyEventClass = KeyEvent.class;
            return f1.get(0).getInt(keyEventClass);
        }
        return -1;
    }
    public static int findMouseInAWT(MouseButton mouseButton){
        return javaFxMouseToRobot.get(mouseButton);
    }
}
