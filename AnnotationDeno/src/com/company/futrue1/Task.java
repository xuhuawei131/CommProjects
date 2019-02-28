package com.company.futrue1;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/1/4 0004.
 */
public class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        Random rand = new Random();
        TimeUnit.SECONDS.sleep(rand.nextInt(12));
        return Thread.currentThread().getName();
    }
}
