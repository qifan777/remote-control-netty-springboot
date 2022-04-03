package com.cheng.api.protocol.client.registry;

import com.cheng.api.protocol.Command;
import com.cheng.api.protocol.CommonRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegistryRequest extends CommonRequest {
    public RegistryRequest() {
        super.setCommand(Command.REGISTRY);
    }
}
