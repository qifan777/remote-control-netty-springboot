package com.cheng.api.protocol.mouse;

import com.cheng.api.protocol.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MouseMoveRequest  extends MouseRequest{
    Double x;
    Double y;
    public MouseMoveRequest() {
        super.setCommand(Command.MOUSE_MOVE);
    }

}
