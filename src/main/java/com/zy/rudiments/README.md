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

三、NIO  
1、缓冲区（Buffer）  
是一个对象，包含一些要写入或要读出的数据。在NIO中，所有数据都是用缓冲区处理的。  
本质上是一个数组。通常是一个字节数组（ByteBuffer），也可以使用其他类型的数组。    
2、通道Channel：  
像自来水管一样，网络数据通过Channel读取和写入。它是双向的。  
3、多路复用器selector：  
Selector会不断轮询注册在其上的Channel，如果某个Channel上面发生读取或写时间，这个channel  
就处理就绪状态，会被selector轮询出来，通过SelectionKey可以获取就绪的集合，进行后续的I/O.