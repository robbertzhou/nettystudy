package com.zy.marshallingcodec;

import com.zy.entity.SubscribeReq;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i=0;i<100;i++){
            SubscribeReq req = new SubscribeReq();
            req.setSubReqID(i);
            req.setRespCode(33);
            req.setDesc("I like the book!");
            ctx.write(req);
        }

        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq)msg;
        System.out.println(req.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
