package com.xuhuawei;

/**
 * Created by Administrator on 2017/1/19 0019.
 */
public class ServerThread extends Thread {
    private boolean stop = false;
    private Queue queue;

    public ServerThread(Queue queue) {
        this.queue = queue;
    }

    public void shutdown() {
        stop = true;
        this.interrupt();
        try {
            this.join();
        } catch (InterruptedException ie) {
        }
    }

    public void run() {
        while (!stop) {
            Request request = queue.getRequest();
            System.out.println("[ServerThread] handle request: " + request);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
            }
        }
        System.out.println("[ServerThread] shutdown.");
    }
}
