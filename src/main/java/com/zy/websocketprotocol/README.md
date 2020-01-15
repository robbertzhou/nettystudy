类讲解：  
1、HttpServerCodec:是HttpRequestDecoder和HttpResponseEncoder的组合，可以简化HTTP的服务器端实现  
2、Netty还提供了一个HttpObjectAggregator类用于解决粘包、拆包现象  
3、在需要将数据从文件系统复制到用户内存中时，可以使用 ChunkedWriteHandler，它支持异步写大型数据流，而又不会导致大量的内存消耗。