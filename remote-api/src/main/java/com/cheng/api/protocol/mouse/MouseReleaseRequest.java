package com.cheng.api.protocol.mouse;

import com.cheng.api.protocol.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MouseReleaseRequest extends MouseRequest {
    int btn;
    public MouseReleaseRequest() {
        super.setCommand(Command.MOUSE_RELEASE);
    }
}
