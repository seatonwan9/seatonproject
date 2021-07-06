package com.xearth.sp.seatonproject.controller;

import java.util.concurrent.Callable;

/**
 * @author wangxudong
 * @date 2020/9/17 15:12
 */
public class CallableDemo implements Callable<String> {
    //成员变量
    int x = 0;
    int y = 0;
    String name = "";

    //构造方法
    public CallableDemo(){ }

    public CallableDemo(int x, int y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        int sum = x + y;
        int sum2 = x + y + 1;
        String ThreadName = Thread.currentThread().getName();
        System.out.println("name：" + name + " sum1：" + sum);
        Thread.sleep(1000);
        System.out.println("name：" + name + " sum2：" + sum2);
        return ThreadName;
    }

}
