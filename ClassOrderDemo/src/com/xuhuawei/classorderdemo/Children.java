package com.xuhuawei.classorderdemo;

public class Children extends Parent {
    {
        System.out.println("--Children 中的初始化块--");
    }
    static{
        System.out.println("--Children 中的Static初始化块--");
    }

    public Children(){
        System.out.println("--Children 中的构造方法--");
    }
}
