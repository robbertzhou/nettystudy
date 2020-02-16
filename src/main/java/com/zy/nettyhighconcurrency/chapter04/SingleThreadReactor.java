package com.zy.nettyhighconcurrency.chapter04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * create 2020-02-16
 * author zhouyu
 * desc 单线程的reactor反应器模式
 */
public class SingleThreadReactor {
    static Logger logger = LoggerFactory.getLogger(SingleThreadReactor.class);
    public static void main(String[] args) throws Exception{
        int k = 1 << 0;
        logger.info("" + k);
        Reactor rr = new Reactor();
        new Thread(rr).start();
    }
}

class Reactor implements Runnable{
    static Logger logger = LoggerFactory.getLogger(Reactor.class);
    Selector selector;
    ServerSocketChannel serverSocket;

    public Reactor(){
        try{
            selector = Selector.open();
            serverSocket = ServerSocketChannel.open();
            serverSocket.socket().bind(new InetSocketAddress(8090));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector,SelectionKey.OP_ACCEPT);
        }catch(Exception ex){

        }finally{

        }
    }
    @Override
    public void run() {
        while(true){
            try{
                int num = selector.select();

                if(num > 0){  //是否大于0
                    Set ss = selector.selectedKeys();
                    Iterator it = ss.iterator();
                    while (it.hasNext()){
                        SelectionKey sk = (SelectionKey) it.next();
                        if(sk.isAcceptable()){
                            ServerSocketChannel ssc = (ServerSocketChannel)sk.channel();
                            SocketChannel sc = ssc.accept();  //tcp三次连接
                            sc.configureBlocking(false);   //配置为非阻塞
                            logger.info("connected....");
                            sc.register(selector,SelectionKey.OP_READ);
//                    break;
                        }
                        if(sk.isReadable()){
                            SocketChannel  channel = (SocketChannel)sk.channel();
                            ByteBuffer buf = ByteBuffer.allocate(1024);
                            long bytesRead = channel.read(buf);
                            while(bytesRead>0){
                                buf.flip();
                                while(buf.hasRemaining()){
                                    System.out.println("receive data is:" +(char)buf.get());
                                }
                                logger.info("readable ...");
                                buf.clear();
                                bytesRead = channel.read(buf);
                            }
                        }
                        it.remove();
                    }
                    ss.clear();
                }

            }catch(Exception ex){
                ex.printStackTrace();
            }finally{

            }
        }
    }
}
