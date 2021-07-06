package com.xearth.sp.seatonproject.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * @author wangxudong
 * @date 2021/7/02 14:45
 */
public class CallableAsynDemo implements Supplier<String> {
    //成员变量
    String name;

    //构造方法
    public CallableAsynDemo(){ }

    public CallableAsynDemo(String name){
        this.name = name;
    }

    @Override
    public String get() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name;
    }
}
