package com.zy.nettyhighconcurrency.chapter03;

import io.netty.buffer.ByteBuf;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @create 2020-01-29
 * @author zhouyu
 * @desc nio丢弃服务器
 */
public class NioDiscardServer {
    public static void main(String[] arg) throws Exception{
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress(8090));
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("niodiscard server is started.");
        while(selector.select() > 0){
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()){
                SelectionKey selectedKey = keys.next();
                if(selectedKey.isAcceptable()){
                    SocketChannel client = channel.accept();
                    if(client!=null){
                        client.configureBlocking(false);
                        client.register(selector,SelectionKey.OP_READ);
                    }

                }else if(selectedKey.isReadable()) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    SocketChannel client = (SocketChannel) selectedKey.channel();
                    int length = 0;
                    while ((length = client.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, length));
                        buffer.clear();
                    }
                    client.close();
                }
                //移除selectedkey
                keys.remove();
            }
        }
    }
}

