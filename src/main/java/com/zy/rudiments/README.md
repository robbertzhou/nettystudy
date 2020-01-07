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

![1578377955675](https://github.com/robbertzhou/nettystudy/blob/master/src/main/resources/%E4%BC%AA%E5%BC%82%E6%AD%A5.png)  
    }  
序号	名称	类型	含义  
1	corePoolSize	int	核心线程池大小  
2	maximumPoolSize	int	最大线程池大小  
3	keepAliveTime	long	线程最大空闲时间  
4	unit	TimeUnit	时间单位  
5	workQueue	BlockingQueue<Runnable>	线程等待队列  
6	threadFactory	ThreadFactory	线程创建工厂  
7	handler	RejectedExecutionHandler	拒绝策略  
如果对这些参数作用有疑惑的请看 ThreadPoolExecutor概述。  
知道了各个参数的作用后，我们开始构造符合我们期待的线程池。首先看JDK给我们预定义的几
