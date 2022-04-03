package com.cheng.client.netty.handler;

import com.cheng.api.handler.MyHandler;
import com.cheng.api.protocol.CommonRequest;
import com.cheng.api.protocol.CommonResponse;
import com.cheng.client.sync.SyncWrite;
import com.cheng.client.sync.WriteFuture;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResponseHandler implements MyHandler {
    @Autowired
    SyncWrite<CommonResponse, CommonRequest> write;

    @Override
    public void handler(Object params, ChannelHandlerContext handlerContext) {

        CommonResponse commonResponse = (CommonResponse) params;
        WriteFuture<CommonResponse> responseWriteFuture = write.writeFutureMap.get(commonResponse.getRequestId());
        if (responseWriteFuture != null) {
            responseWriteFuture.set(commonResponse);
        }
    }
}
