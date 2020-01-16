package com.zy.nettyconcurrenydesc;

/**
 * @create 2020-01-16
 * @author zhouyu
 * desc:计算1+2+3+……1000000000的和
 */
public class NotForkJointTask {
    public static void main(String[] args){
        new Thread(()->{
           Long sum = 0l;
           long maxSize = 1000000000l;
           long startTime = System.currentTimeMillis();
           for (long i = 1l;i<=maxSize;i++){
               sum += i;
           }
           System.out.println("result is :" + sum);
           long endTime = System.currentTimeMillis();
           System.out.println("costTime :" + (endTime - startTime));
        }).start();
    }
}
