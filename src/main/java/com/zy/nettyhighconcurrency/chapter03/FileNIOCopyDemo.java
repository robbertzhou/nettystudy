package com.zy.nettyhighconcurrency.chapter03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @create 2020-01-27
 * @author zhouyu
 * @desc 文件复制demo
 */
public class FileNIOCopyDemo {
    public static void main(String[] args){
        nioCopyFile("d:\\question.txt","d:\\question_dest.txt");
    }

    public static void nioCopyFile(String srcPath,String destPath){
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        try{
            if(!destFile.exists()){
                destFile.createNewFile();
            }
            long startTime = System.currentTimeMillis();
            FileInputStream fis = null;
            FileOutputStream fos = null;
            FileChannel inChannel = null;
            FileChannel outChannel = null;
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            int length = -1;
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while((length = inChannel.read(buf))!= -1){
                buf.flip();
                int outlength = 0;
                while((outlength = outChannel.write(buf)) != 0){
                    System.out.println("write byte number is :" + outlength);
                }
                buf.clear();
            }
            outChannel.force(true);
            long endTime = System.currentTimeMillis();
        }catch (Exception ex){

        }finally {

        }

    }
}
