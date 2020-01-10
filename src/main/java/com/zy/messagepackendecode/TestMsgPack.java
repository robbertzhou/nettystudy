package com.zy.messagepackendecode;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.util.ArrayList;
import java.util.List;

public class TestMsgPack {
    public static void main(String[] args) throws Exception{
        List<String> src = new ArrayList<>();
        src.add("msgpack");
        src.add("kumosf");
        src.add("viver");
        MessagePack msgpack = new MessagePack();
        byte[] raw = msgpack.write(src);
        List<String> dst = msgpack.read(raw, Templates.tList(Templates.TString));
        for(String content : dst){
            System.out.println(content);
        }
    }
}
