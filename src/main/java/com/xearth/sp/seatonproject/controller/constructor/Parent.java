package com.xearth.sp.seatonproject.controller.constructor;

public class Parent {

    protected int number;

    public Parent() {
        System.out.println("调用Parent无参构造方法");
    }

    public Parent(int a) {
        this.number = 1028;
        System.out.println("调用Parent构造方法" + a);
    }

    public int getNumber() {
        return number;
    }
}
