package com.productandcousmer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 79282
 * 产品池
 */
public class ProductPool {
    private List<Products> productsList;
    private int maxSize ;
    public ProductPool(int maxSize) {
        this.maxSize = maxSize;
        this.productsList = new LinkedList<>();
    }

    //生产产品
    public synchronized void push(Products products) {

            if (productsList.size() == maxSize) {
                //产品池满了 不需要生产
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //开始生产
            this.productsList.add(products);
            System.out.println("生产了产品" + Thread.currentThread().getId());
            this.notifyAll();
    }

    //消费产品
    public synchronized Products pop() {
        if (productsList.size() == 0) {
            //产品池空了 停止消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //开始消费
        Products products = this.productsList.remove(0);
        System.out.println("消费了产品" + Thread.currentThread().getId());
        this.notifyAll();

        return products;
    }


}
