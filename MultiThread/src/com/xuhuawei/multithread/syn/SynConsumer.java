package com.xuhuawei.multithread.syn;

import com.xuhuawei.multithread.BaseThread;

import java.util.Queue;

public class SynConsumer extends BaseThread {


    public SynConsumer(Queue<Integer> queue) {
        super(queue);
    }

    @Override
    public void run() {
        consumer();
    }

    private void consumer() {
        while (isRuning) {
            synchronized (queue) {
                while (queue.size() == 0) {
                    queue.notify();
                    System.out.println("当前队列为空");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.poll();
                queue.notify();
                System.out.println("消费者消费一条任务，当前队列长度为" + queue.size());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }

}
