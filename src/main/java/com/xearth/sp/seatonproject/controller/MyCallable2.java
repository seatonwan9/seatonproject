package com.xearth.sp.seatonproject.controller;

import java.util.concurrent.Callable;

/**
 * @author wangxudong
 * @date 2020/9/17 15:12
 */
public class MyCallable2 {
    //成员变量
    int x = 5;
    int y = 3;

    //构造方法
    public MyCallable2(){
    }

    public MyCallable2(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Integer call() {
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                System.out.println("i:"+i+", j:"+j);
            }
        }
        return x+y;
    }

}
