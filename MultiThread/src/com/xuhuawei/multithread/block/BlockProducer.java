package com.xuhuawei.multithread.block;

import java.util.concurrent.BlockingQueue;

public class BlockProducer extends BlockBaseThread {
    public BlockProducer(BlockingQueue<Integer> queue) {
        super(queue);
    }

    @Override
    public void run() {
        producer();
    }

    private void producer() {
        while (true) {
            try {
                queue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产者生产一条任务，当前队列长度为" + queue.size());
            try {
                Thread.sleep( 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
