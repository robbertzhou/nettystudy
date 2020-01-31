package com.zy.nettyhighconcurrency.chapter04;

import java.nio.channels.*;

/**
 * @create 2020-01-30
 * @author zhouyu
 * @desc 服务器跟客户端建立连接的业务处理
 */
public class AcceptorHandler implements Runnable{
    ServerSocketChannel serverSocketChannel;
    Selector selector;
    public AcceptorHandler(ServerSocketChannel ssc, Selector selector){
        this.serverSocketChannel = ssc;

        this.selector = selector;
        try {
            SocketChannel channel = ssc.accept();
            channel.register(selector, SelectionKey.OP_READ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try{

        }catch (Exception ex){

        }
    }
}
