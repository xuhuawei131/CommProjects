package com.xuhuawei;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2017/2/3 0003.
 */
public class ServiceQueue extends Thread  {
    public static List<String> arrayList;

    public ServiceQueue() {
        arrayList = new CopyOnWriteArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            Iterator<String> iterator = arrayList.iterator();
            while (iterator.hasNext()) {
                String bean = iterator.next();
                try {
                    System.out.println("size:"+arrayList.size());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                arrayList.remove(bean);
            }
            try {
                this.wait();
            } catch (InterruptedException ie) {

            }
        }
    }

    public void addNewTask(String task) {
            arrayList.add(task);
            this.notifyAll();
    }

}
