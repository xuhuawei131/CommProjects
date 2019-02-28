package com.company.future2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2017/1/4 0004.
 * http://www.xuebuyuan.com/2005904.html
 */
public class CallableTest {
    public static void main(String args[]) {
        testRunnable();
//        testCallable();
    }

    private static void testCallable() {

        ExecutorService pool = Executors.newFixedThreadPool(3);

        CalculateCallable task1 = new CalculateCallable(0);
        CalculateCallable task2 = new CalculateCallable(1);
        CalculateCallable task3 = new CalculateCallable(3);

        try {
            // 提交并执行任务，任务启动时返回了一个 Future对象，
            // 如果想得到任务执行的结果或者是异常可对这个Future对象进行操作
            Future<Integer> future1 = pool.submit(task1);
            // 获得第一个任务的结果，如果调用get方法，当前线程会等待任务执行完毕后才往下执行
            System.out.println("task1 get: " + future1.get());

            Future<Integer> future2 = pool.submit(task2);
            // System.out.println("task2 get: " + future2.get(2000,
            // TimeUnit.MILLISECONDS));
            // 等待5秒后，再停止第二个任务，因为第二个任务进行的是无限循环
            Thread.sleep(8000);
            System.out.println("task2 cancel: " + future2.cancel(true));

            Future<Integer> future3 = pool.submit(task3);
            System.out.println("task3 get: " + future3.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 停止任务执行服务
        pool.shutdownNow();
    }

    private static void testRunnable() {
        CalculateRunnable task1 = new CalculateRunnable(0);
        CalculateRunnable task2 = new CalculateRunnable(1);
        CalculateRunnable task3 = new CalculateRunnable(3);

        ExecutorService pool = Executors.newFixedThreadPool(3);

        try {
            Future<?> future1 = pool.submit(task1);
            System.out.println("task1 get: " + future1.get());

            Future<?> future2 = pool.submit(task2);
            // System.out.println("task2 get: " + future2.get());

            // 等待5秒后，再停止第二个任务
            Thread.sleep(8000);
            System.out.println("task2 cancel: " + future2.cancel(true));

            Future<?> future3 = pool.submit(task3);
            System.out.println("task3 get: " + future3.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 停止任务执行服务
        pool.shutdownNow();
    }


}
