package com.Runnable01;

/**
 * @author 79282
 * 使用实现Runnable接口方式
 * 可以用this
 */
public class Window implements Runnable {
    static int ticket = 100;
    @Override
    public void run() {
        while (true) {
            //this 表示当前对象
            synchronized (this) {
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
        Window w = new Window();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);
        t1.start();
        t2.start();
        t3.start();
    }
}