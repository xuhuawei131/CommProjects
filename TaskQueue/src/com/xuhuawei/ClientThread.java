package com.xuhuawei;



/**
 * Created by Administrator on 2017/1/19 0019.
 */
public class ClientThread extends Thread {
    private Queue queue;
    private String clientName;

    public ClientThread(Queue queue, String clientName) {
        this.queue = queue;
        this.clientName = clientName;
    }

    public String toString() {
        return "[ClientThread-" + clientName + "]";
    }

    public void run() {
        for(int i=0; i<100; i++) {
            Request request = new Request("" + (long)(Math.random()*10000));
            System.out.println(this + " send request: " + request);
            queue.putRequest(request);
            try {
                Thread.sleep((long)(Math.random() * 10000 + 1000));
            }
            catch(InterruptedException ie) {
            }
        }
        System.out.println(this + " shutdown.");
    }
}