package com.zy.marshallingcodec;

import com.zy.entity.SubscribeReq;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq)msg;
        System.out.println("server receive data is :" + req.toString());
        ctx.writeAndFlush(req);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
