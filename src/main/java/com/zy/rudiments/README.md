NIO入门知识：  
网络编程的基本模型就是C/S模型。也就是两个进程之间的通信。  
服务提供IP地址和port，客户端通过连接向服务端监听的socket发起请求。    
1、block io  
2、NIO
3、AIO

一、BIO  
简单的TimeServer，Socket client= ServerSocket.accept(),线程阻塞。  
有个缺点，每个client都要一个线程处理。  
编程有个地方：out = new PrintWriter(socket.getOutputStream(),true);//默认为false，手动flush到网络channel。    

二、伪异步的BIO：  
架构图：    

![1578377955675](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1578377955675.png)