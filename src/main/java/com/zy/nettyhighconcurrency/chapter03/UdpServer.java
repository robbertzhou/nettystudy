package com.zy.nettyhighconcurrency.chapter03;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * @create 2020-01-29
 * @author zhouyu
 * @desc udp数据包接收服务端
 */
public class UdpServer {

    public static void main(String[] args) throws Exception{
        new UdpServer().receive();
    }

    public void receive() throws Exception{
        //get dataprgram channel
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        datagramChannel.bind(new InetSocketAddress("localhost",8090));
        System.out.println("upd server is started.");
        Selector selector = Selector.open();
        datagramChannel.register(selector, SelectionKey.OP_READ);
        //通过选择器，查询IO事件
        while(selector.select() > 0){
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //迭代IO事件
            while(keys.hasNext()){
                SelectionKey selectionKey = keys.next();
                if(selectionKey.isReadable()){
                    SocketAddress client = datagramChannel.receive(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array(),0,buffer.limit()));
                    buffer.clear();
                }
            }
            keys.remove();
        }
        selector.close();
        datagramChannel.close();
    }
}
