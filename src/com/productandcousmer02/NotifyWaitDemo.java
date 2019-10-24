package com.productandcousmer02;

/**
 * 线程间通信 :
 * 1.使用生产者 消费者
 * 2.通知等待唤醒机制
 *
 * 判断,干活,通知
 * 使用synchronized实现
 *
 */
public class NotifyWaitDemo {
    public static void main(String[] args) {
        Products products = new Products();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                try {
                    products.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();



            new Thread(()-> {
                for (int i = 0; i < 10; i++) {
                    try {
                        products.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"B").start();
        }
    }



/**
 * 资源类
 */
class Products {
    private int num = 0;
    public synchronized void increment() throws InterruptedException {
        //1.判断
        if (num != 0) {
            //有产品 不需要生产 等待
            this.wait();
        }
        //2.干活
        ++num;
        System.out.println(Thread.currentThread().getName() + "\t" + num);

        //3.通知
        this.notifyAll();

    }

    public synchronized void decrement() throws InterruptedException {
        //1.判断
        if (num == 0) {
            //没有产品 不能消费 等待
            this.wait();
        }
        //2.干活
        --num;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        //3.通知
        this.notifyAll();
    }

}
