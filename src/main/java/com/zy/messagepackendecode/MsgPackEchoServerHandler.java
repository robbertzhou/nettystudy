package com.zy.messagepackendecode;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.msgpack.type.ArrayValue;


public class MsgPackEchoServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            ArrayValue uis = (ArrayValue)msg;
            System.out.println("jjj");
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
