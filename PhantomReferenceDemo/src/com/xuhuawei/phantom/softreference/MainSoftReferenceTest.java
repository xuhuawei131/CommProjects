package com.xuhuawei.phantom.softreference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class MainSoftReferenceTest {

    public static void main(String[] args) {
        run2();
    }

    private static void run1(){
        Map<Integer, SoftRefedPilot> map = new HashMap<Integer, SoftRefedPilot>(10000000);
        ReferenceQueue<Pilot> queue = new ReferenceQueue<Pilot>();

        int i = 0;
        while (i < 10000000) {
            Pilot p = new Pilot();
            map.put(i, new SoftRefedPilot(i, p, queue));
            //p = null;
            SoftRefedPilot pollref = (SoftRefedPilot) queue.poll();
            if (pollref != null) {//找出被软引用回收的对象
                //以key为标志，从map中移除
                map.remove(pollref.key);
                System.out.println("add i="+i+" remove="+pollref.key);
            }
            System.out.println("pollref="+pollref);

            if (i%10000==0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
        System.out.println("done");

    }
    private static void run2(){
//        Map<Integer, SoftReference<Pilot>> map = new HashMap<Integer, SoftReference<Pilot>>(10000000);
        Map<Integer, WeakReference<Pilot>> map = new HashMap<Integer, WeakReference<Pilot>>(10000000);
        for (int i = 0; i < 1000000; i++) {
            Pilot bean=new Pilot();
//            SoftReference<Pilot> sr=new SoftReference<Pilot>(bean);
            WeakReference<Pilot> sr=new WeakReference<Pilot>(bean);
            map.put(i,sr);
            if (i==50000){
                // 强制垃圾回收
                System.gc();
                // 调用finalize方法
                System.runFinalization();
                System.out.println(map.size()+"**** GC ****");
            }
        }



        System.out.println("done");
    }
}

