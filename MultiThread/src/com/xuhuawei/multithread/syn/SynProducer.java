package com.xuhuawei.multithread.syn;

import com.xuhuawei.multithread.BaseThread;

import java.util.Queue;

public class SynProducer extends BaseThread {
    private final int MAX_LEN = 10;
    public SynProducer(Queue<Integer> queue) {
        super(queue);
    }

    @Override
    public void run() {
        producer();
    }

    private void producer() {
        while (isRuning) {
            synchronized (queue) {
                while (queue.size() == MAX_LEN) {
                    queue.notify();
                    System.out.println("当前队列满");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.add(1);
                queue.notify();
                System.out.println("生产者生产一条任务，当前队列长度为" + queue.size());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
