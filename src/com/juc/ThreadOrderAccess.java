package com.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程定制化调用通信
 */
public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource sr = new ShareResource();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sr.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sr.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sr.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

    }
}

/**
 * 资源类
 */
class ShareResource {
    private int number = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition c1 = lock.newCondition();
    private final Condition c2 = lock.newCondition();
    private final Condition c3 = lock.newCondition();

    public void print5(int totalLoopNumber) throws InterruptedException {
        lock.lock();
        try {
            //1 判断
            while (number != 1) {
                //A 就要停止
                c1.await();
            }
            //2 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t totalLoopNumber: " + totalLoopNumber);
            }
            //3 通知
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(int totalLoopNumber)throws InterruptedException {
        lock.lock();
        try {
            //1 判断
            while (number != 2) {
                //A 就要停止
                c2.await();
            }
            //2 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t totalLoopNumber: " + totalLoopNumber);
            }
            //3 通知
            number = 3;
            c3.signal();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(int totalLoopNumber)throws InterruptedException {
        lock.lock();
        try {
            //1 判断
            while (number != 3) {
                //A 就要停止
                c3.await();
            }
            //2 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t totalLoopNumber: " + totalLoopNumber);
            }
            //3 通知
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}





