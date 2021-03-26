package com.xearth.sp.seatonproject.controller.constructor;

public class Subparent extends Parent {
    public Subparent(int b) {
        super(b);
        System.out.println("调用Subparent构造方法");
    }

    public Subparent() {
        super();
        System.out.println("调用Subparent无参构造方法");
    }
}
