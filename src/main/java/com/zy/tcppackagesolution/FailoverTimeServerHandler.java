package com.zy.tcppackagesolution;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

@ChannelHandler.Sharable
public class FailoverTimeServerHandler extends ChannelHandlerAdapter {
    private int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String body = new String(req);
        int index = body.indexOf(System.getProperty("line.separator"));
        if(index == -1){
            index = body.length();
        }
        body = body.substring(0,index);
        System.out.println("receive data is :" + body + ";counter=" + (++counter));

        String currentTime = body.equalsIgnoreCase("QUERY TIME ORDER")?
                new Date().toString():"BAD ORDER";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
