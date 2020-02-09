package com.zy.nettyhighconcurrency.chapter07;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

public class FixedLengthFrameDecoderTest {

    public static void main(String[] args){
        FixedLengthFrameDecoderTest test = new FixedLengthFrameDecoderTest();
        test.testFramesDecoded();;
    }
    public void testFramesDecoded() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        channel.writeInbound(input.retain());
        channel.finish();

        ByteBuf read = channel.readInbound();
        buf.readSlice(3);
        read.release();

//        read = channel.readInbound();
//        Assert.assertEquals(buf.readSlice(3), read);
//        read.release();
//
//        read = channel.readInbound();
//        Assert.assertEquals(buf.readSlice(3), read);
//        read.release();
//
//        Assert.assertNull(channel.readInbound());
        buf.release();
    }

    //
    public void testFramesDecoded2() {
//        ByteBuf buf = Unpooled.buffer();
//        for (int i = 0; i < 9; i++) {
//            buf.writeByte(i);
//        }
//        ByteBuf input = buf.duplicate();
//        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
//        Assert.assertFalse(channel.writeInbound(input.readBytes(2)));
//        Assert.assertTrue(channel.writeInbound(input.readBytes(7)));
//        Assert.assertTrue(channel.finish());
//        ByteBuf read = channel.readInbound();
//        Assert.assertEquals(buf.readSlice(3), read);
//        read.release();
//
//        read = channel.readInbound();
//        Assert.assertEquals(buf.readSlice(3), read);
//        read.release();
//
//        read = channel.readInbound();
//        Assert.assertEquals(buf.readSlice(3), read);
//        read.release();
//
//        Assert.assertNull(channel.readInbound());
//        buf.release();
    }
}
