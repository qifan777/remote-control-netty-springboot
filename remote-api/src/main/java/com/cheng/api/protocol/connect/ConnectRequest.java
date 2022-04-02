package com.cheng.api.protocol.connect;

import com.cheng.api.protocol.Command;
import com.cheng.api.protocol.CommonRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConnectRequest extends CommonRequest {
    public ConnectRequest() {
        super.setCommand(Command.CONNECT);
    }

}
