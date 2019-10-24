package com.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 卖票 (使用Lock实现)
 * 多线程  操作 资源类
 */

class Ticket02{
    private int numbers = 30;
    private final Lock lock = new ReentrantLock();

    public  void sale() {
        lock.lock();
        try {
            if (numbers > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第" + numbers-- + "剩余" + numbers);
            }
        } finally {
            lock.unlock();
        }


    }

}


public class SaleTicket02 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        for (int i = 0; i < 40 ; i++) {

            new Thread(()->{
                ticket.sale();
            },"A").start();

            new Thread(()->{
                ticket.sale();
            },"B").start();

        }
    }
}
