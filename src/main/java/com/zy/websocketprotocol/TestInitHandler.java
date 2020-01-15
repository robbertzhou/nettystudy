package com.zy.websocketprotocol;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @created 2020-01-15
 * @author zhouyu
 * 功能描述：server channel初始化器，初始管道
 */
public class TestInitHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //http协议的编解码使用的,是HttpRequestDecoder和HttpResponseEncoder处理器组合
        //HttpRequestDecoder http请求的解码
        //HttpResponseEncoder http请求的编码
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new TestServerHandler());
    }
}
