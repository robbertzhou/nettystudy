package com.zy.dingchangfenge;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class FixedLengthEchoHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body  = (String)msg;
        System.out.println("receive client data is :" + body);
        ctx.writeAndFlush("send client.");
    }
}
