package com.productandconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 使用无界阻塞队列linkblockingQueue实现生产者消费者
 */
class ProductPool {

    private BlockingQueue producstPool = new LinkedBlockingQueue();

    public ProductPool(BlockingQueue producstPool) {
        this.producstPool = producstPool;
    }

    /**
     * 生成产品
     */
    public void product(Integer product) throws InterruptedException {
        producstPool.put( product);
        System.out.println(Thread.currentThread().getName()+"生成了"+product+"号产品");
    }

    /**
     * 消费产品
     * @throws InterruptedException
     */
    public void consum() throws InterruptedException {
        Integer take = (Integer) producstPool.take();
        System.out.println(Thread.currentThread().getName()+"消费了"+take+"号产品");
    }
}


/**
 * @author 79282
 */
public class LinkBlockKingQueue {


    public static void main(String[] args) {
        ProductPool productPool = new ProductPool(new LinkedBlockingQueue());
        new Thread(()->{
            for (int i = 1; i <= 50; i++) {
                try {
                    productPool.product(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"生产者线程").start();


        new Thread(()->{
            for (int i = 1; i <= 50; i++) {
                try {
                    productPool.consum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"消费者线程").start();
    }

}
