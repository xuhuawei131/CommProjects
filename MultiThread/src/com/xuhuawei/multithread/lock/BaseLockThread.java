package com.xuhuawei.multithread.lock;

import com.xuhuawei.multithread.BaseThread;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BaseLockThread extends BaseThread {

    protected  Lock lock = new ReentrantLock();
    protected  Condition condition = lock.newCondition();

    public BaseLockThread(Queue<Integer> queue,Lock lock,Condition condition) {
        super(queue);
        this.lock=lock;
        this.condition=condition;
    }

}
