package com.company.futrue1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/1/4 0004.
 * http://blog.csdn.net/andycpp/article/details/8902655
 */
public class FutureTest {
    public static void main(String args[]) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            Callable<String> c = new Task();
            MyFutureTask ft = new MyFutureTask(c);
            executor.submit(ft);
        }
        executor.shutdown();
    }
}
