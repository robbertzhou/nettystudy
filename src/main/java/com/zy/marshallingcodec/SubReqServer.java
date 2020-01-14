package com.zy.marshallingcodec;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/***
 * Marshalling版本图书订购代码\
 * sss
 */
public class SubReqServer {
    public static void main(String[] args) throws Exception{
        new SubReqServer().bind(8090);
    }

    public void bind(int port) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .option(ChannelOption.SO_BACKLOG,100)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(MarshallingCodeFactory.buildMarshallingDecoder());
                            ch.pipeline().addLast(MarshallingCodeFactory.buildMarshallingEncoder());
                            ch.pipeline().addLast(new SubReqServerHandler());
                        }
                    });
            ChannelFuture f = bootstrap.bind(port).sync();
            f.channel().closeFuture().sync();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
