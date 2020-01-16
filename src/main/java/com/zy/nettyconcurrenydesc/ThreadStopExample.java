package com.zy.nettyconcurrenydesc;

/**
 * create: 2020-01-16
 * @author zhouyu
 * desc:描述没有加volatile的变量导致程序一直运行。
 */
public class ThreadStopExample {
    public static boolean stop;
    public static void main(String[] args) throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!stop){
                    System.out.println("test----");
                }
            }
        }).start();
        Thread.sleep(3000);
        stop = true;
    }
}
