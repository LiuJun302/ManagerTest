package LockTest;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class UseReentrantReadWriteLock {

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private ReadLock readLock = rwLock.readLock();
    private WriteLock writeLock = rwLock.writeLock();
    public void read(){
        try {
            readLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void write(){
        try {
            writeLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {

        final UseReentrantReadWriteLock urrw = new UseReentrantReadWriteLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.read();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.read();
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.write();
            }
        }, "t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.write();
            }
        }, "t4");

        /**
         * 两个读锁：
         * 当前线程:t2进入...
         * 当前线程:t1进入...
         * 当前线程:t2退出...
         * 当前线程:t1退出...
         * 结论：两个都是读锁，可以同时进入
         */
		t1.start();
		t2.start();

        /**
         * 读写两个锁：
         * 当前线程:t1进入...
         * 当前线程:t1退出...
         * 当前线程:t3进入...
         * 当前线程:t3退出...
         * 结论：读写两个锁：互斥进入，谁先抢到锁，谁先进入，下个线程只有等前一个锁释放了才能进
         */
//		t1.start(); // R
//		t3.start(); // W

        /**
         * 两个写锁：
         * 当前线程:t3进入...
         * 当前线程:t3退出...
         * 当前线程:t4进入...
         * 当前线程:t4退出...
         * 结论：互斥
         */
//        t3.start();
//        t4.start();
    }
}
