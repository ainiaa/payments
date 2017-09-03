package com.a91coding.payments.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;


public class BaseModel implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
