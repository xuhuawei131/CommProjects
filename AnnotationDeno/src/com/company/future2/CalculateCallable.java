package com.company.future2;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2017/1/4 0004.
 */
public class CalculateCallable implements Callable<Integer> {
    private int param = -1;

    public CalculateCallable(int param) {
        this.param = param;
    }

    @Override
    public Integer call() throws Exception {
        if (this.param == 0) {
            System.out.println("no loop********" + param);
            return 0;
        } else if (this.param == 1) {

            // 如果flag的值为1，做一个无限循环
            try {
                while (true) {
                    System.out.println("while looping********" + param);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            return 1;
        } else {
            throw new Exception("illegal argument!" + param);
        }
    }
}
