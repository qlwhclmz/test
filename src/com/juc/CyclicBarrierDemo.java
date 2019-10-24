package com.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * @author 79282
 * CyclicBarrier
 * 的字面意思是可循环（Cyclic）使用的屏障（Barrier）。它要做的事情是，
 * 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 * 直到最后一个线程到达屏障时，屏障才会开门，所有
 * 被屏障拦截的线程才会继续干活。
 * 线程进入屏障通过CyclicBarrier的await()方法。
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("可召唤神龙"); });

        for (int i = 1; i <=7 ; i++) {
            int temp = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+"获得第"+temp+"个龙珠");
                    //线程运行到这里 阻塞
                    //需要等全部线程都运行到这个点之后才会开门
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
