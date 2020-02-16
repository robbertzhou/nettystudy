package com.zy.nettyhighconcurrency.chapter04;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * create 2020-02-16
 * author zhouyu
 * desc 同步的一个连接一个处理器
 */
public class ConnectPerHandler {
    public static void main(String[] args) throws Exception{
        try{
            ServerSocket serverSocket = new ServerSocket(8090);
            while (true){
                Socket client =  serverSocket.accept();
                new PerHandler(client).start();
                Thread.sleep(500);
            }

        }catch(Exception ex){

        }
    }
}

class PerHandler extends Thread{
    Socket client = null;
    public PerHandler(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        while (true){
            try{

                InputStream is = client.getInputStream();
                byte[] input = new byte[is.available()];
                is.read(input);
                System.out.println("receive data is:" + new String(input));
                byte[] output = "hello world!".getBytes();
                client.getOutputStream().write(output);
            }catch(Exception ex){

            }finally{

            }
        }
    }
}
