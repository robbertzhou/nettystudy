主要内容：  
1、TCP粘包/拆包的基础知识。  
2、没考虑TCP粘包/拆包的问题案例。  
3、使用Netty解决读半包问题。  
MTU: Maxitum Transmission Unit 最大传输单元  
MSS: Maxitum Segment Size 最大分段大小  
PPPoE: PPP Over Ethernet（在以太网上承载PPP协议），就是因为这个协议的出现我们才有必要修改我们的MSS或者是MTU值。
MTU最大传输单元，这个最大传输单元实际上和链路层协议有着密切的关系，EthernetII帧的结构DMAC+SMAC+Type+Data+CRC

