package com.company.future2;

/**
 * Created by Administrator on 2017/1/4 0004.
 */
public class CalculateRunnable implements Runnable {
    private int param = -1;

    public CalculateRunnable(int param) {
        this.param = param;
    }

    @Override
    public void run() {
        if (this.param == 0) {
            System.out.println("param=" + param + "***over");
        } else if (this.param == 1) {

            // 如果flag的值为1，做一个无限循环
            try {
                while (true) {
                    System.out.println("looping********" + param);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            System.out.println("param=" + param + "***over");
        } else {
            System.out.println("illegal argument!" + param);
        }
    }
}
