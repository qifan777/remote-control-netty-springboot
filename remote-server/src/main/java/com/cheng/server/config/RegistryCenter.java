package com.cheng.server.config;

import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RegistryCenter {
    public final Map<String, Channel> userChannel = new ConcurrentHashMap<>();
    public final Map<String, String> userIdChannelId = new ConcurrentHashMap<>();

    public Channel getUserChannel(String id) {
        return userChannel.get(id);
    }

    public void putUserChannel(String id, Channel channel) {
        userChannel.put(id, channel);
    }

    public void removeUserChannel(String id) {
        userChannel.remove(id);
    }

    public void putUserIdChannelId(String userId, String channelId) {
        userIdChannelId.put(userId, channelId);
    }

    public void removeUserIdChannelId(String userId) {
        userIdChannelId.remove(userId);
    }

    public String getUserChannelId(String userId) {
        return userIdChannelId.get(userId);
    }
}
