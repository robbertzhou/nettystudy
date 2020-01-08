package com.zy.rudiments;



import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class MultiplexerTimerServer implements Runnable {
    private int port;
    private Selector selector;
    private ServerSocketChannel serverChannel;
    private volatile boolean isStop = false;
    private volatile static List<SocketChannel> clients = new ArrayList<>();

    public MultiplexerTimerServer(int port){
        try{
            this.port = port;
            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);  //默认为阻塞，false为非阻塞
            serverChannel.socket().bind(new InetSocketAddress(8090),1024);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while (true){
//                        try{
//                            Thread.sleep(2000);
//                            for (int i = 0;i<clients.size();i++){
//                                doWrite(clients.get(i),"Hello world.\n");
//                            }
//                        }catch (Exception ex){
//
//                        }
//
//                    }
//                }
//            }).start();
        }catch (Exception ex){
            ex.printStackTrace();
            System.exit(-1);
        }

    }

    private void sendClient()  {
        while (true){
            try{
                Thread.sleep(2000);
                for (int i = 0;i<clients.size();i++){
                    doWrite(clients.get(i),"Hello world.");
                }
            }catch (Exception ex){

            }

        }
    }
    public void setStop(){
        this.isStop = true;
    }
    @Override
    public void run() {
        while (!isStop){
            try{
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try{
                        handleInput(key);
                    }catch (Exception ex){

                    }
                }
            }catch (Exception ex){

            }

        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){ //有效
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                SocketChannel chennel = ssc.accept();
//                clients.add(chennel);
                chennel.configureBlocking(false);
                chennel.register(selector,SelectionKey.OP_READ);
            }
            if(key.isReadable()){  //有读的事件
                //read the data
                SocketChannel sc = (SocketChannel)key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if(readBytes >0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes,"UTF-8");
                    System.out.println("The time server receive data is :" + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?new Date().toString():"Bad order";
                    doWrite(sc,currentTime);
                }else if(readBytes < 0){
                    key.cancel();
                    sc.close();
                }else{
                    ;
                }
            }

        }
    }

    private static void doWrite(SocketChannel channel,String message) throws IOException{
        if(message!=null && message.trim().length() >0){
            byte[] bytes = message.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            channel.write(buffer);
        }
    }
}
