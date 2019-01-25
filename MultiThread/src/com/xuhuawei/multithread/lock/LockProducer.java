package com.xuhuawei.multithread.lock;

import com.xuhuawei.multithread.BaseThread;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class LockProducer extends BaseLockThread {

    private final int MAX_LEN = 10;

    public LockProducer(Queue<Integer> queue, Lock lock, Condition condition) {
        super(queue, lock, condition);
    }

    @Override
    public void run() {
        producer();
    }

    private void producer() {
        while (isRuning) {
            lock.lock();
            try {
                while (queue.size() == MAX_LEN) {
                    System.out.println("当前队列满");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                    }
                }
                queue.add(1);
                condition.signal();
                System.out.println("生产者生产一条任务，当前队列长度为" + queue.size());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            } finally {
                lock.unlock();
            }
        }
    }

}
