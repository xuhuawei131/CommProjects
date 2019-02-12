package com.xuhuawei.phantom.phantom;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceTest {

    public static void main(String[] args) {
        // 创建一个字符串对象
//        String str = new String("我爱学java");
        String str = "我爱学java";

        // 创建一个引用队列
        ReferenceQueue rq = new ReferenceQueue();
        // 创建一个虚引用,让虚引用 引用到"我爱学java"
        PhantomReference pr = new PhantomReference(str, rq);

        // 切断str和我爱学java字符串之间的引用
        str = null;
        // 并不能通过虚引用获取被引用的对象,输出就为null
        System.out.println(pr.get());

        // 强制垃圾回收
        System.gc();
        // 调用finalize方法
        System.runFinalization();

        // 垃圾回收之后,虚引用将被放在引用队列中
        //取出引用队列中最先进入队列引用与pr进行比较
        System.out.println(rq.poll() == pr);

    }
}
