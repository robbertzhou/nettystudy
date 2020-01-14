jboss-marshalling-1.4.0和jboss-marshalling-serial-1.4.0  
问题解决：  
1、使用marshalling时，对象没有继承java.io.Serializable接口，导致不能encoder。  
2、MarshallingDecoder  decoder = new MarshallingDecoder(provider,1024);   
当设为10时，大于的时候就会出现问题.1024大于一个对象序列化的字节数。