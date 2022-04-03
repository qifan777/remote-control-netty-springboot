package com.cheng.client.sync;

import com.cheng.api.protocol.CommonResponse;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class WriteFutureImpl<R extends CommonResponse> implements WriteFuture<R> {
    R response;
    CountDownLatch countDownLatch = new CountDownLatch(1);
    Throwable cause;
    boolean isTimeout = false;

    @Override
    public R get(long timeout) throws InterruptedException {
        boolean await = countDownLatch.await(timeout, TimeUnit.MILLISECONDS);
        if (await) {
            return response;
        }
        isTimeout = true;
        cause = new Exception("响应超时");
        return null;
    }

    @Override
    public void set(R response) {
        this.response = response;
        countDownLatch.countDown();
    }

    @Override
    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }

    @Override
    public boolean isTimeout() {
        return isTimeout;
    }

}
