package com.zy.messagepackendecode;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

public class MsgPackEchoClientHandler extends ChannelHandlerAdapter {
    private final int sendNumber = 1000;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] uis = userInfos();
        for (UserInfo uu :uis){
            ctx.write(uu);
        }
        ctx.flush();
//        ctx.writeAndFlush(uis);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UserInfo[] uis = (UserInfo[])msg;
        for (UserInfo ui : uis){
            System.out.println("client receive the msg :" + ui.getName());

        }

//        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private UserInfo[] userInfos(){
        UserInfo[] uis = new UserInfo[sendNumber];
        for (int i=0;i<sendNumber;i++){
            UserInfo userInfo = new UserInfo();
            userInfo.setAge(i);
            userInfo.setName("ABCD-->" + i);
            uis[i] = userInfo;
        }
        return uis;
    }
}
