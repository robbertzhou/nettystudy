package com.zy.rudiments;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.util.concurrent.ExecutionException;

/**
 * bio 方式的TimeClient
 */
public class BioTimeClilent {
    public static void main(String[] args){
        int port = 8090;
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try{
            socket = new Socket("localhost",port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);  //如果不放入true的话，不能自动写入.
            Thread.sleep(100);

            out.println("QUERY TIME ORDER\n");

            String resp = in.readLine();
            System.out.println("Now is: " + resp);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
