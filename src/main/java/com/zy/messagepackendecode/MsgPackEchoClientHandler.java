package com.zy.messagepackendecode;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class MsgPackEchoClientHandler extends ChannelHandlerAdapter {
    private final int sendNumber = 10;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] uis = userInfos();
        for (UserInfo uu :uis){
            ctx.write(uu);
        }
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client receive the msg :" + msg);
        ctx.write(msg);
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
