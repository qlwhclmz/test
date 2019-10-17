package com.Thread01;

/**
 * @author 79282
 * Thread使用同步代码块(不适用于同步方法)
 * 要想保证线程的安全，必须要求所有的线程共用同一把锁！
 *
 * 1.对于非静态的方法而言，使用同步的话，默认锁为：this。如果使用在继承的方式实现(Thread)多线程的话，慎用！
 * 	2.对于静态的方法，如果使用同步，默认的锁为：当前类本身。以单例的懒汉式为例。 Class clazz = Singleton.class
 */
public class Window extends Thread {
    static int ticket = 100;
    static Object obj = new Object();

    @Override
    public void run() {

        while (true) {
            synchronized (obj) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName()+"卖票:"+"票号为:"+ticket--);
                }

            }
        }

    }
}

 class Test{
     public static void main(String[] args) {
         Window window01 = new Window();
         Window window02 = new Window();
         Window window03 = new Window();
         window01.start();
         window02.start();
         window03.start();
     }
}
