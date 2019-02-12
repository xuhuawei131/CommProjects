package com.xuhuawei.multithread;

import com.xuhuawei.multithread.lock.LockConsumer;
import com.xuhuawei.multithread.lock.LockProducer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
         Lock lock = new ReentrantLock();
         Condition condition = lock.newCondition();

//        BaseThread producer = new SynProducer(queue);
//        BaseThread consumer = new SynConsumer(queue);

        BaseThread producer = new LockProducer(queue,lock,condition);
        BaseThread consumer = new LockConsumer(queue,lock,condition);
        producer.start();
        consumer.start();

        try {
            Thread.sleep(20*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.stopRunning();
        consumer.stopRunning();
    }
}
