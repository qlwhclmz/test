package com.productandcousmer;

/**
 * @author 79282
 * 消费者
 */
public class Consumer extends Thread {
    private ProductPool productPool;

    public Consumer(ProductPool productPool) {
        this.productPool = productPool;
    }
    //
    @Override
    public void run() {
        while (true) {

            Products pop = this.productPool.pop();
            System.out.println("消费了" + pop.getName());

        }
    }
}
