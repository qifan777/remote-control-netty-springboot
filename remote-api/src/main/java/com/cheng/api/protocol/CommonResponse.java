package com.cheng.api.protocol;

import lombok.Data;

@Data
public abstract class CommonResponse {
    String userId;
    String command;
}
