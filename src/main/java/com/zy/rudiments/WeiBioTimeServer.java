package com.zy.rudiments;

import java.net.ServerSocket;
import java.net.Socket;

public class WeiBioTimeServer {
    public static void main(String[] args) throws Exception{
        int port = 8090;
        ServerSocket server = null;
        try{
            server = new ServerSocket(port);
            System.out.println("Time server is started at " + port);
            TimeServerHandlerExecutePool executePool = new TimeServerHandlerExecutePool(10,100);
            Socket socket = null;  //表示一个连接的客户端
            while(true){  //轮询创建一个socket连接
                socket = server.accept();  //阻塞主线程的语句
                Runnable task = new BioTimeServerHandler(socket);
                executePool.execute(task);
            }

        }catch(Exception ex){

        }finally {
            //优雅进行一些关闭动作
            if(server != null){
                System.out.println("The time server is stop.");
                server.close();
                server = null;
            }
        }
    }
}
