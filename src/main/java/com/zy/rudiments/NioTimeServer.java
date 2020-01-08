package com.zy.rudiments;

public class NioTimeServer {
    public static void main(String[] args){
        MultiplexerTimerServer server = new MultiplexerTimerServer(8090);
        new Thread(server,"Nio-server").start();
    }
}
