package com.zy.dingchangfenge;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @created 2020-01-10
 * @author zhouyu
 * 特殊分割符的netty客户端
 */
public class DelimiterEchoClient {
    static DelimiterEchoClientHandler clientHandler = new DelimiterEchoClientHandler();
    public static volatile ChannelHandlerContext ctx;
    public static void main(String[] args) throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new DelimiterEchoClient().connect("localhost",8090);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(3000);
        for(int i=0;i<1000;i++){
            clientHandler.sendMsg("Hi,Zhouyu.Welcome to netty.$_");
        }

    }

    public void connect(String host,int port) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .option(ChannelOption.TCP_NODELAY,true)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ByteBuf bb = Unpooled.copiedBuffer("$_".getBytes());
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,bb));
                        ch.pipeline().addLast(new StringDecoder());

                        ch.pipeline().addLast(clientHandler);
                    }
                });
        ChannelFuture f = bootstrap.connect(host,port).sync();

        f.channel().closeFuture().sync();

    }
}
