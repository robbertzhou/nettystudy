package com.zy.messagepackendecode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

public class MsppackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        try{
            MessagePack msgpack = new MessagePack();
            byte[] raw = msgpack.write(msg);
            out.writeBytes(raw);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
