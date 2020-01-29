package com.zy.nettyhighconcurrency.chapter03;

import javax.xml.crypto.Data;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @create 2020-01-29
 * @author zhouyu
 * @desc UDP客户端演示
 */
public class UdpClient {
    public static void main(String[] args) throws Exception{
        new UdpClient().send();
    }

    public void send() throws Exception{
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        buffer.flip();
        buffer.put("hello world".getBytes());
        buffer.flip();
        datagramChannel.send(buffer,new InetSocketAddress("localhost",8090));
        buffer.clear();
//        datagramChannel.close();
    }
}
