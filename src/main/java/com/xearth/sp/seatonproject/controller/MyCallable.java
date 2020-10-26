package com.xearth.sp.seatonproject.controller;

import java.util.concurrent.Callable;

/**
 * @author wangxudong
 * @date 2020/9/17 15:12
 */
public class MyCallable implements Callable<Integer> {
    //成员变量
    int x = 5;
    int y = 3;

    //构造方法
    public MyCallable(){
    }

    public MyCallable(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("线程开始：" + Thread.currentThread().getName());

        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                System.out.println("i:"+i+", j:"+j);
            }
        }
        return x+y;
    }

}
