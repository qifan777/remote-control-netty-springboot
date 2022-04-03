package com.cheng.api.protocol.client.mouse;

import com.cheng.api.protocol.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MousePressRequest extends  MouseRequest{
    int btn;
    public MousePressRequest() {
        super.setCommand(Command.MOUSE_PRESS);
    }
}
