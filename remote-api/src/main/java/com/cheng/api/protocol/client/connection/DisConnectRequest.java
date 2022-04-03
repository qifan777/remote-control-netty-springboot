package com.cheng.api.protocol.client.connection;

import com.cheng.api.protocol.Command;
import com.cheng.api.protocol.CommonRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DisConnectRequest extends CommonRequest {
    public DisConnectRequest() {
        super.setCommand(Command.DISCONNECT);
    }
}
