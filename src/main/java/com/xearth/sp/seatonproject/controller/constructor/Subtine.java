package com.xearth.sp.seatonproject.controller.constructor;

public class Subtine extends Subparent {
    public Subtine(int c) {
        super(c);
        System.out.println("调用Subtine构造方法");
    }

    public Subtine() {
        super();
        System.out.println("调用Subtine无参构造方法");
    }
}
