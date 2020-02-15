package com.zy.nettyhighconcurrency.chapter03;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * create 2020-02-15
 * author zhouyu
 * desc 文件通道的操作
 */
public class FileChannelOperators {
    static Logger logger = LoggerFactory.getLogger(FileChannelOperators.class);
    public static void main(String[] args) throws Exception{
//        readData("data/test.txt");
        writeData();
    }


    /**
     * 从文件获取输入流通道
     * @param filePath
     * @return
     */
    public static FileChannel getFileChannelFromInput(String filePath) throws Exception{
        FileInputStream fis = new FileInputStream(new File(filePath));
        return fis.getChannel();
    }

    /**
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static FileChannel getFileChannelFromOutput(String filePath) throws Exception{
        FileOutputStream fos = new FileOutputStream(filePath);
        return fos.getChannel();
    }

    public static void readData(String filePath) throws Exception{
        FileChannel fc = getFileChannelFromInput("data/test.txt");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int lenght = -1;
        int count = 0;

        while((lenght = fc.read(buffer)) >0){
            buffer.flip();
            while(buffer.hasRemaining()){
                System.out.println("read data is :" + (char)buffer.get() + "," +count++);
            }
        }
    }

    public static void writeData() throws Exception{
        FileChannel fc = getFileChannelFromOutput("data/test11.txt");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        logger.info("allocated status:position=" + buffer.position() + ",limit=" + buffer.limit());
        buffer.clear();
        logger.info("clear status:position=" + buffer.position() + ",limit=" + buffer.limit());
        for (int i=0;i<10;i++){
            buffer.put("a".getBytes());
        }
        logger.info("puted status:position=" + buffer.position() + ",limit=" + buffer.limit());
        buffer.flip();
        logger.info("flip status:position=" + buffer.position() + ",limit=" + buffer.limit());
        fc.write(buffer);
        fc.force(false);
        fc.close();

    }
}
