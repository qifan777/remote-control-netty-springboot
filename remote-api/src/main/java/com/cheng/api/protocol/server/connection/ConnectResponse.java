package com.cheng.api.protocol.server.connection;

import com.cheng.api.protocol.Command;
import com.cheng.api.protocol.CommonResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConnectResponse extends CommonResponse {
    public ConnectResponse(){
        super.setCommand(Command.CONNECT_RESPONSE);
    }
}
