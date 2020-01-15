package com.zy.websocketprotocol;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @create 2020-01-15
 * @author zhouyu
 * 简单的httpserver
 */
public class TestServer {
    public static void main(String[] args) throws Exception{
        new TestServer().bind(8090);
    }

    public void bind(int port) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,100)
                .childHandler(new TestInitHandler());
        ChannelFuture f = bootstrap.bind(port).sync();
        f.channel().closeFuture().sync();
    }
}
