package com.cheng.client.sync;

import com.cheng.api.protocol.CommonRequest;
import com.cheng.api.protocol.CommonResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SyncWrite<R extends CommonResponse, Q extends CommonRequest> {
    public Map<String, WriteFuture<R>> writeFutureMap = new ConcurrentHashMap<>();

    public R write(Channel ctx, Q request, long timeout) throws Throwable {
        request.setRequestId(UUID.randomUUID().toString());
        WriteFutureImpl<R> future = new WriteFutureImpl<>();
        writeFutureMap.put(request.getRequestId(), future);
        ctx.writeAndFlush(request).addListener((ChannelFutureListener) channelFuture -> {
            if (!channelFuture.isSuccess()) {
                log.info("发送消息失败");
                future.setCause(channelFuture.cause());
                writeFutureMap.remove(request.getRequestId());
            }
        });
        R response = future.get(timeout);
        writeFutureMap.remove(request.getRequestId());
        if (response == null) {
            throw future.getCause();
        }
        return response;
    }
}
