package com.zy.messagepackendecode;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.msgpack.type.ArrayValue;


public class MsgPackEchoServerHandler extends ChannelHandlerAdapter {
    private int counter = 0;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            UserInfo[] uis = (UserInfo[])msg;
            System.out.println("run counter is :" + (++counter));
            for (UserInfo ui : uis){
                System.out.println("name is : " + ui.getName() + ";age=" + ui.getAge());
            }
           ctx.writeAndFlush(uis);


        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
