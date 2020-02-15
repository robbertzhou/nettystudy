package com.zy.nettyhighconcurrency.chapter03;

import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * create 2020-02-15
 * author zhouyu
 *
 */
public class BufferReaderTest {
    public static void main(String[] args) throws Exception{
        RandomAccessFile file = new RandomAccessFile("data/test.txt","rw");
        FileChannel fc = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int byteRead = fc.read(buffer);
        buffer.put("k".getBytes());
//        用 flip() 方法将 Buffer f从写入模式切换到读取模式。调用 flip() 将 position 设置回0，并将 limit 置为刚才的位置
        buffer.flip();
        Buffer bf = buffer.mark();
        System.out.println("capacity:" + buffer.capacity() + ",position:" + buffer.position() + ",limit:" + buffer.limit());
        while (buffer.hasRemaining()){
            System.out.println("read "+ buffer.position()+" char:" + (char)buffer.get());
        }
//        uffer.rewind() 将 position 设置回0，因此你可以重读缓冲区中的所有数据。这个 limit 保持不变，因此仍然标记有多少元素(字节、字符等)可以从Buffer读取。
//        buffer.rewind();
//        while (buffer.hasRemaining()){
//            System.out.println("read "+ buffer.position()+" char:" + (char)buffer.get());
//        }
        buffer.reset();
        while (buffer.hasRemaining()){
            System.out.println("read "+ buffer.position()+" char:" + (char)buffer.get());
        }
//        while (byteRead !=-1){
//            buffer.flip();
//            while (buffer.hasRemaining()){
//                System.out.println("read char:" + (char)buffer.get());
//            }
//            fc.read(buffer);
//        }
    }
}
