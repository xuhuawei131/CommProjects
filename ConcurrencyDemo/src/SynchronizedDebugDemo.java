public class SynchronizedDebugDemo implements Runnable{
    static SynchronizedDebugDemo instance=new SynchronizedDebugDemo();

    @Override
    public void run() {
        synchronized (this){
            System.out.println("我是对象锁的代码块形式，我叫"+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"运行结束");
        }
    }
    public static void main(String[] args) {
        Thread thread1=new Thread(instance);
        Thread thread2=new Thread(instance);
        thread1.start();
        thread2.start();
        while (thread1.isAlive()||thread2.isAlive()){

        }
        System.out.println("finished");

    }
}
