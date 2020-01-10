package com.zy.messagepackendecode;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MsgPackEchoServer {
    public static void main(String[] args) throws Exception{
        new MsgPackEchoServer().bind("localhost",8090);
    }

    public void bind(String host,int port) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workerGroup)
                .option(ChannelOption.SO_BACKLOG,1024)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ch.pipeline().addLast("encoder",new MsppackEncoder());
                        ch.pipeline().addLast("decoder",new MsgpackDecoder());

                        ch.pipeline().addLast(new MsgPackEchoServerHandler());
                    }
                });
        ChannelFuture f = bootstrap.bind(port).sync();
        f.channel().closeFuture().sync();
    }
}
