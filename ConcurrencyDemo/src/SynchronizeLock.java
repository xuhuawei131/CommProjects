import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizeLock {

    Lock lock=new ReentrantLock();

    public synchronized void method1(){
        System.out.println("我是Synchronized形式的锁");
    }

    public void method2(){
        lock.lock();
        try{
            System.out.println("我是lock形式的锁");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SynchronizeLock instance=new SynchronizeLock();
        instance.method1();
        instance.method2();
    }
}
