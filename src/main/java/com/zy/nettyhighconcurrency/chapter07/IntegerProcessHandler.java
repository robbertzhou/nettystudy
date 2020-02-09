package com.zy.nettyhighconcurrency.chapter07;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * create 2020-02-09
 * author zhouyu
 * desc integer业务处理
 */
public class IntegerProcessHandler extends SimpleChannelInboundHandler {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        Integer integer = (Integer)o;
        System.out.println("打印一个整数为：" + integer);
    }
}
