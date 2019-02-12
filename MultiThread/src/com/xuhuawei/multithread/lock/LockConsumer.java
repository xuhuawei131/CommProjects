package com.xuhuawei.multithread.lock;

import com.xuhuawei.multithread.BaseThread;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class LockConsumer extends BaseLockThread {
    public LockConsumer(Queue<Integer> queue,Lock lock,Condition condition) {
        super(queue,  lock,  condition);
    }

    @Override
    public void run() {
        consumer();
    }

    private void consumer() {
        while (isRuning) {
            lock.lock();
            try {
                while (queue.size() == 0) {
                    System.out.println("当前队列为空");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                    }
                }
                queue.poll();
                condition.signal();
                System.out.println("消费者消费一条任务，当前队列长度为" + queue.size());
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
