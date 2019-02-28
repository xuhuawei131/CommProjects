package com.company.futrue1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2017/1/4 0004.
 */
public class MyFutureTask extends FutureTask<String> {
    public MyFutureTask(Callable<String> callable) {
        super(callable);
    }
    @Override
    protected void done() {
        try {
            System.out.println(get() + " 线程执行完毕！~");
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
