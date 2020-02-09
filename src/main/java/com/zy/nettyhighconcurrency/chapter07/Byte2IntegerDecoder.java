package com.zy.nettyhighconcurrency.chapter07;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * create 2020-02-09
 * author zhouyu
 * desc bytebuf 转换成int
 */
public class Byte2IntegerDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        while(byteBuf.readableBytes() >= 4){
            int i = byteBuf.readInt();
            list.add(i);
        }
    }
}
