package com.xuhuawei.classorderdemo;

public class Parent {
    {
        System.out.println("--Parent 中的初始化块--");
    }
    static{
        System.out.println("--Parent 中的Static初始化块--");
    }

    public Parent(){
        System.out.println("--Parent 中的构造方法--");
    }
}
