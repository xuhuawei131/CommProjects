package com.xuhuawei.multithread.block;

import com.xuhuawei.multithread.BaseThread;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockBaseThread extends Thread {
    protected BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(10);
    public BlockBaseThread(BlockingQueue<Integer> queue) {
        this.queue=queue;
    }
}
