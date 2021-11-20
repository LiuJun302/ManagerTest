package others;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SyncTest {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    Lock lock = new ReentrantLock();    //注意这个地方
    public static void main(String[] args)  {
        final SyncTest test = new SyncTest();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();
    }

    public void insert(Thread thread) {
        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            for(int i=0;i<5;i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }
    }
}



/*
import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SyncTest {
    private int value = 0;
    Lock lock = new ReentrantLock();
    public synchronized void addValueSync(){
        this.value ++;
        System.out.println(Thread.currentThread().getName()+" : "+value);
    }
    public void addValueLock(){
        try{
         lock.lock();
         value ++;
         System.out.println(Thread.currentThread().getName()+" : "+value);
        }finally {
            lock.unlock();
        }
    }

    static int num = 10;
    public static synchronized void foo(){
        num = num  + 1;
        System.out.println(num);
    }
    public static void main(String[] args) {
        final SyncTest st = new SyncTest();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        foo();
                    }
                }
        ).start();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        foo();
                    }
                }
        ).start();
    }
}
*/
