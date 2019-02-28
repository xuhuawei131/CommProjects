package com.xuhuawei;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/19 0019.
 */
public class Queue {
    private List<Request> queue = new LinkedList();

    public synchronized Request getRequest() {
        while(queue.size()==0) {
            try {
                this.wait();
            }
            catch(InterruptedException ie) {
                return null;
            }
        }
        return (Request)queue.remove(0);
    }

    public synchronized void putRequest(Request request) {
        queue.add(request);
        this.notifyAll();
    }
}
