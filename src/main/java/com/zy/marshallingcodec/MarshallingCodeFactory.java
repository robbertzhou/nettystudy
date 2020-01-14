package com.zy.marshallingcodec;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

public class MarshallingCodeFactory {
    /**
     * 创建Marshalling解码器MarshallingDecoder
     * @return
     */
    public static MarshallingDecoder buildMarshallingDecoder(){
        /**
         * 利用Marshalling工具类的静态方法getProvidedMarshallerFactory获取MarshallerFactory实例
         */
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        /**
         * 创建MarshallingConfiguration对象，设置其版本为5
         */
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        /**
         * 创建UnmarshallerProvider实例
         */
        UnmarshallerProvider provider=new DefaultUnmarshallerProvider(marshallerFactory,configuration);
        /**
         * 通过构造函数创建Netty的MarshallingDecoder对象，参数为UnmarshallerProvider和单个消息序列化后的最大长度
         */
        MarshallingDecoder  decoder = new MarshallingDecoder(provider,1024);
        return decoder;
    }



    public static MarshallingEncoder buildMarshallingEncoder(){
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        /**
         * 创建MarshallerProvider对象
         */
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory,configuration);
        /**
         * MarshallingEncoder用于将实现序列化接口的pojo对象序列化为二进制数组
         */
        MarshallingEncoder encoder =new MarshallingEncoder(provider);
        return encoder;
    }
}
