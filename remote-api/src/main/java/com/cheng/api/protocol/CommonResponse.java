package com.cheng.api.protocol;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class CommonResponse  implements Serializable {
    String userId;
    String command;
    String requestId;
    Boolean isSuccess;
}
