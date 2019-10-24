package com.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程定制化调用通信
 */
public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            try {
                shareResource.print5(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                shareResource.print10(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();


        new Thread(()->{
            try {
                shareResource.print15(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
    }
}

/**
 * 资源类
 */
class ShareResource{
    private int num = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();

    public void print5(int number) throws InterruptedException {
        //1.判断
        while (number != 1) {
            condition1.await();
        }

        //2.干活
        for (int i = 1; i <=5 ; i++) {
            System.out.println(Thread.currentThread().getName()+num);
        }
        //3.通知
        condition2.signal();
    }
    public void print10(int number) throws InterruptedException {
        //1.判断
        while (number != 2) {
            condition2.await();
        }

        //2.干活
        for (int i = 1; i <=10 ; i++) {
            System.out.println(Thread.currentThread().getName()+num);
        }
        //3.通知
        condition3.signal();
    }

    public void print15(int number) throws InterruptedException {
        //1.判断
        while (number != 3) {
            condition3.await();
        }

        //2.干活
        for (int i = 1; i <=15 ; i++) {
            System.out.println(Thread.currentThread().getName()+num);
        }
        //3.通知
        num = 1;
        condition1.signal();
    }



}
