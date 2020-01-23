package com.zy.messagepackendecode;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.AbstractReferenceCountedByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class MsgpackEchoClient {
    public static void main(String[] args) throws Exception{

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .option(ChannelOption.TCP_NODELAY,true)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
                        ch.pipeline().addLast("decoder",new MsgpackDecoder());
                        ch.pipeline().addLast("encoder",new MsppackEncoder());
                        ch.pipeline().addLast(new MsgPackEchoClientHandler());
                    }
                });

        ChannelFuture f = bootstrap.connect("localhost",8090).sync();
        f.channel().closeFuture().sync();
    }
}
