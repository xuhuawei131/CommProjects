package com.xuhuawei.classloaderdemo;


public class ClassLoaderParentMain {

    public static void main(String[] args){
        ClassLoader ClassLoader1 = ClassLoaderParentMain.class.getClassLoader();
        ClassLoader ClassLoader2 = ClassLoader1.getParent();
        ClassLoader ClassLoader3 = ClassLoader2.getParent();

        System.out.println(ClassLoader1);
        System.out.println(ClassLoader2);
        System.out.println(ClassLoader3);
    }
}
