package com.cheng.client.netty.handler;

import com.cheng.api.codec.ObjDecoder;
import com.cheng.api.codec.ObjEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Autowired
    MyClientHandler clientHandler;

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 0))
                .addLast(new ObjDecoder())
                .addLast(new ObjEncoder())
                .addLast(clientHandler);
    }
}
