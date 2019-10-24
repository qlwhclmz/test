package com.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author 79282
 * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 * 其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 * 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
 */
public class CountDownDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(7);
        for (int i = 1; i <= 7; i++) {
            int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "第" + temp + "号学生离开了教室");
                //让计数器减一
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //当计数器==0时,阻塞
        countDownLatch.await();
        System.out.println("关上大门");
    }
}
