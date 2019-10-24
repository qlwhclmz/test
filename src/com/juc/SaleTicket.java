package com.juc;

/**
 * 卖票 (使用synchronize实现)
 * 多线程  操作 资源类
 */

class Ticket{
    private int numbers = 30;

    public synchronized void sale() {
        if (numbers > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出第" + numbers-- + "剩余" + numbers);
        }
    }

}



public class SaleTicket {
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
