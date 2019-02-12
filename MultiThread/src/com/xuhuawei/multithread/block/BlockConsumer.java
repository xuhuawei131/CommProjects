package com.xuhuawei.multithread.block;

import java.util.concurrent.BlockingQueue;

public class BlockConsumer extends BlockBaseThread {

    public BlockConsumer(BlockingQueue<Integer> queue) {
        super(queue);
    }

    @Override
    public void run() {
        consumer();
    }

    private void consumer() {
        while (true) {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费者消费一条任务，当前队列长度为" + queue.size());
            try {
                Thread.sleep( 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
