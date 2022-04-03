package com.cheng.api.protocol.client.screen;

import com.cheng.api.protocol.Command;
import com.cheng.api.protocol.CommonRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ScreenPictureRequest extends CommonRequest {
    byte[] image;
    public ScreenPictureRequest() {
        super.setCommand(Command.SCREEN_PICTURE);
    }
}
