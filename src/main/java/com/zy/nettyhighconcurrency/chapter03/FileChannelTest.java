package com.zy.nettyhighconcurrency.chapter03;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @create 2020-01-23
 * @author zhouyu
 * @desc 文件管道
 */
public class FileChannelTest {
    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream("d:\\question.txt");
        FileChannel in =  fis.getChannel();  //获取文件channel
        System.out.println("position is " + in.position());
        System.out.println("limit is " + in.size());
        ByteBuffer buf = ByteBuffer.allocate((int) in.size());
        int length = -1;
        byte[] arr = new byte[(int) in.size()];
        int index = 0;
        while((length = in.read(buf))!=-1){
            try{
                arr[index++] = buf.get(index);
            }catch (Exception ex){
                break;
            }

        }
        System.out.println(new String(arr));
    }
}
