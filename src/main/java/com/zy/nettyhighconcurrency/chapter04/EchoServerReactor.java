package com.zy.nettyhighconcurrency.chapter04;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @create 2020-01-30
 * @author zhouyu
 * @desc reactor反应器模式服务端
 */
public class EchoServerReactor implements Runnable{
    public static void main(String[] args) throws Exception{
        new EchoServerReactor().run();
    }

    Selector selector;
    ServerSocketChannel serverSocketChannel;

    public EchoServerReactor() throws Exception{
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8090));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                 Iterator<SelectionKey> keys = selected.iterator();
                 while (keys.hasNext()){
                     SelectionKey sk = keys.next();
                     if (sk.isAcceptable()){

                         AcceptorHandler acceptorHandler = new AcceptorHandler
                                 ((ServerSocketChannel)sk.channel(),selector);
                     }else if (sk.isReadable()){
                         EchoHandler eh = new EchoHandler();
                     }

                 }
                 selected.clear();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    void dispatch(SelectionKey sk){
        Runnable handler = (Runnable)sk.attachment();
        if(handler!=null){
            handler.run();
        }
    }
}
