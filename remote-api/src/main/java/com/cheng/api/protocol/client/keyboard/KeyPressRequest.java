package com.cheng.api.protocol.client.keyboard;

import com.cheng.api.protocol.Command;
import com.cheng.api.protocol.CommonRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class KeyPressRequest extends CommonRequest {
    int keyCode;

    public KeyPressRequest() {
        super.setCommand(Command.KEY_PRESS);
    }
}
