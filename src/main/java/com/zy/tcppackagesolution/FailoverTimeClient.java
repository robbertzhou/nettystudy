package com.zy.tcppackagesolution;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.Socket;


public class FailoverTimeClient {

    public static void main(String[] args) throws Exception{
        int port = 8090;
        new FailoverTimeClient().connect(port,"localhost");
    }

    public void connect(int port,String host) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap strap = new Bootstrap();
            strap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new FailoverTimeClientHandler());
                        }
                    });
            ChannelFuture f = strap.connect(host,port);
            f.channel().closeFuture().sync();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
