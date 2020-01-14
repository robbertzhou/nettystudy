package com.zy.entity;

import java.io.Serializable;

/**
 * 订阅实体
 */
public class SubscribeReq implements Serializable {
    private int subReqID;
    private int respCode;
    private String desc;

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    //    resp.setSubReqID(i);
//        resp.setRespCode(0);
//        resp.setDesc("I love China");
//        return resp;


    @Override
    public String toString() {
        return "订单号：" + this.getSubReqID();
    }
}
