package com.zy.websocketprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @created 2020-01-15
 * @author zhouyu
 * 功能描述：http server的业务处理。
 */
public class TestServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof HttpRequest){
            //read msg
            HttpRequest request = (HttpRequest)msg;
            System.out.println("request method name is :" + request.getMethod().name());
            //write msg to client
            ByteBuf content = Unpooled.copiedBuffer("Hello world", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,content);
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,content.readableBytes());
            ctx.writeAndFlush(response);
            ctx.channel().close();
        }
    }
}
