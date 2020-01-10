tcp上层协议应用有四种方式：  
1、定长方式  
2、换行结束标志，如ftp  
3、特殊分割符  
4、消息头方式，如在消息头定义消息长度。  
这节两个知识点：DelimiterBasedFrameCoder和FixedLengthBasedFrameCoder.  

delimiter  
n.  
定界符，分隔符  

@Sharable 注解用来说明ChannelHandler是否可以在多个channel直接共享使用  