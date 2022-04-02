package com.cheng.client.config;

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
    @Value("${remote.server.id}")
    String userId;
    String friendId;
}
