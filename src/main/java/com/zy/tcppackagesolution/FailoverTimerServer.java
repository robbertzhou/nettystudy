package com.zy.tcppackagesolution;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jdk.nashorn.internal.runtime.linker.Bootstrap;

public class FailoverTimerServer {
    public static void main(String[] args){
        int port = 8090;

        System.out.println(System.getProperty("line.separator"));
        try{
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workGrouer = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGrouer)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new FailoverTimeServerHandler());
            ChannelFuture f = bootstrap.bind(port).sync();
            f.channel().closeFuture();
        }catch (Exception ex){

        }
    }
}
