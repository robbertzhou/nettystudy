package com.zy.tcppackagesolution;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class CorrectTimeServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String)msg;
        System.out.println("receive data is :" + body);
        String currentTime = body.equalsIgnoreCase("QUERY TIME ORDER")?new Date().toString():"BAD ORDER";
        currentTime += System.getProperty("line.separator");
        ByteBuf bb = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(bb);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
