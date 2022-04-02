package com.cheng.api.codec;

import com.cheng.api.protocol.CommonRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class ObjEncoder extends MessageToByteEncoder<CommonRequest> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CommonRequest commonRequest, ByteBuf byteBuf) throws Exception {
        //串行化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(commonRequest);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }
}
