package com.cheng.api.protocol;

public interface Command {
    public final String CONNECT = "connect";
    public final String DISCONNECT = "disconnect";
    public final String SCREEN_PICTURE = "screenPicture";
    public final String MOUSE_MOVE = "mouseMove";
    public final String MOUSE_PRESS = "mousePress";
    public final String MOUSE_RELEASE = "mouseRelease";
    public final String MOUSE_WHEEL = "mouseWheel";
    public final String KEY_PRESS = "keyPress";
    public final String KEY_RELEASE = "keyRelease";
    public final String REGISTRY = "registry";
    public final String CONNECT_RESPONSE = "connectResponse";
}
