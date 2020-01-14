package com.zy.marshallingcodec;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SubReqClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(MarshallingCodeFactory.buildMarshallingDecoder());
                        ch.pipeline().addLast(MarshallingCodeFactory.buildMarshallingEncoder());

                        ch.pipeline().addLast(new SubReqClientHandler());
                    }
                });
        ChannelFuture f = bootstrap.connect("localhost",8090).sync();
        f.channel().closeFuture().sync();
    }
}
