package com.zy.tcppackagesolution;

import com.sun.javafx.image.ByteToBytePixelConverter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class CorrectTimeClientHandler extends ChannelHandlerAdapter {
    byte[] resp = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i=0;i<100;i++){
            ByteBuf bb = Unpooled.copiedBuffer(resp);
            ctx.writeAndFlush(bb);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String)msg;
        System.out.println("receive server's data is :" + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
