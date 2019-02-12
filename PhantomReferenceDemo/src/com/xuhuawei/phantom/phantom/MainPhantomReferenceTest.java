package com.xuhuawei.phantom.phantom;

import com.xuhuawei.phantom.softreference.Pilot;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class MainPhantomReferenceTest {

    public static void main(String[] args) {

        ReferenceQueue<String> queue = new ReferenceQueue<String>();

        // 创建一个字符串对象
        String str = new String("我爱学java");
        // 创建弱引用,让此弱引用引用到"我爱学java"字符串
        WeakReference<String> wr = new WeakReference(str,queue);
        // 切断str和我爱学java字符串之间的引用
        str = null;
        // 去除弱引用所引用的对象
        System.out.println("去除弱引用所引用的对象： "+wr.get());

        // 强制垃圾回收
        System.gc();
        // 调用finalize方法
        System.runFinalization();
        WeakReference<String> wr1= (WeakReference<String>) queue.poll();
        if (wr1!=null){
           String delete= wr1.get();
            System.out.println("队列删除："+delete);
        }


        // 再次取出弱引用的对象
        System.out.println("再次取出弱引用的对象："+wr.get());


        //=================================================================


        Object obj = wr.get();
        if (obj == null) {
            // 重建一个
            wr = new WeakReference(recreateIt());
            obj =wr.get();
            System.out.println("重建一个："+obj);

        }

        //再次切断
        obj =null;
        System.out.println("再次切断："+obj);

    }

    private static Object recreateIt() {

        String str = new String("新的字符串对象");

        return str;
    }
}
