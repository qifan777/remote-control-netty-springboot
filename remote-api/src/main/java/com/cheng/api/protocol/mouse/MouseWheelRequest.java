package com.cheng.api.protocol.mouse;

import com.cheng.api.protocol.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MouseWheelRequest extends MouseRequest {

    //true向下
    //false向上
    Boolean direction;

    public MouseWheelRequest() {
        setCommand(Command.MOUSE_WHEEL);
    }
}
