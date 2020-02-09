package com.zy.nettyhighconcurrency.chapter07;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;

public class Byte2IntegerDecoderTester {
    public static void main(String[] args){
        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel embeddedChannel) throws Exception {
                embeddedChannel.pipeline().addLast(new Byte2IntegerDecoder());
                embeddedChannel.pipeline().addLast(new IntegerProcessHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(new Byte2IntegerDecoder());
        ByteBuf buf = Unpooled.buffer();
        for (int j=0;j<10;j++){

            buf.writeByte(j);

        }
        ByteBuf input = buf.duplicate();
        channel.writeInbound(input.retain());
        channel.flush();

        try{
            Thread.sleep(Integer.MAX_VALUE);
        }catch (Exception ex){

        }

    }
}
