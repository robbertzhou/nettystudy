package com.zy.tcppackagesolution;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author zy
 * @date 2020-01-09
 */
public class CorrectTimeServer {
    public static void main(String[] args){
        int port = 8090;

        try{
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workGroup = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new CorrectChildServerHandler());
            ChannelFuture f = bootstrap.bind(new InetSocketAddress(8090)).sync();
            f.channel().closeFuture().sync();


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
