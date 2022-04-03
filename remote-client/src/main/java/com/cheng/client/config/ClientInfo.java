package com.cheng.client.config;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ClientInfo {
    @Value("${remote.server.port}")
    Integer port;
    @Value("${remote.server.host}")
    String host;
    String userId;
    String friendId;

    public ClientInfo() {
        this.userId = RandomUtil.randomString(10);
    }
}
