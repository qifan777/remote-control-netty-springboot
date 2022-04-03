package com.cheng.client.sync;

import com.cheng.api.protocol.CommonResponse;

public interface WriteFuture<R extends CommonResponse> {

    R get(long timeout) throws InterruptedException;

    void set(R response);

    void setCause(Throwable course);

    Throwable getCause();

    boolean isTimeout();
}
