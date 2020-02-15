package com.zy.nettyhighconcurrency.chapter03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.IntBuffer;

/**
 * create 2020-02-15
 * author zhouyu
 * desc 测试buffer的allocate方法
 */
public class UseBuffer {
    static Logger logger = LoggerFactory.getLogger(UseBuffer.class);
    public static void main(String[] args) throws Exception{
//        allocateMethod();
        putMethod();
    }

    //allocate方法
    public static void allocateMethod(){
        IntBuffer intBuffer = IntBuffer.allocate(20);
        logger.info("-------allocate buffer----------");
        logger.info("posistion=" + intBuffer.position());
        logger.info("capacity=" + intBuffer.capacity());
        logger.info("limited=" + intBuffer.limit());
    }

    //put方法
    public static void putMethod(){
        IntBuffer intBuffer = IntBuffer.allocate(20);
        for (int i=0;i<5;i++){
            intBuffer.put(i);
        }
        logger.info("-------put buffer----------");
        intBuffer.flip();
        intBuffer.flip();
        logger.info("posistion=" + intBuffer.position());
        logger.info("capacity=" + intBuffer.capacity());
        logger.info("limited=" + intBuffer.limit());
    }
}
