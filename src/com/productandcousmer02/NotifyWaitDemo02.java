package com.productandcousmer02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间通信 :
 * 1.使用生产者 消费者
 * 2.通知等待唤醒机制
 *
 * 判断,干活,通知
 * 使用synchronized实现
 *
 * 当出现四个线程时 会产生虚假唤醒

 *         使用Condition 对标实现synchronize
 *
 */
class ShareData02//资源类
{
    private int number = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();


    public synchronized void increment() throws InterruptedException
    {
        //判断
        while(number!=0) {
           condition.await();
        }
        //干活
        ++number;
        System.out.println(Thread.currentThread().getName()+" \t "+number);
        //通知
        condition.signalAll();
    }

    public synchronized void decrement() throws InterruptedException
    {
        //判断
        while (number != 1) {
            condition.await();

        }

        //干活
        --number;
        System.out.println(Thread.currentThread().getName()+" \t "+number);
        //通知
        condition.signalAll();

    }
}


public class NotifyWaitDemo02
{
    public static void main(String[] args)
    {
        ShareData sd = new ShareData();
        new Thread(() -> {

            for (int i = 1; i <= 10; i++) {
                try {
                    sd.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {

            for (int i = 1; i <= 10; i++) {
                try {
                    sd.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {

            for (int i = 1; i <= 10; i++) {
                try {
                    sd.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {

            for (int i = 1; i <= 10; i++) {
                try {
                    sd.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}










