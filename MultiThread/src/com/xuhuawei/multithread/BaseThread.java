package com.xuhuawei.multithread;

import java.util.Queue;

public class BaseThread extends Thread{

    protected  Queue<Integer> queue;
    protected volatile boolean isRuning=true;

    public BaseThread(Queue<Integer> queue) {
        this.queue = queue;
    }

    public void stopRunning(){
        try{
            isRuning=false;
            interrupt();
        }catch (Exception e){

        }
    }
}
