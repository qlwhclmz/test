package com.productandcousmer;

/**
 * @author 79282
 */
public class Test01 {
    public static void main(String[] args) {
       ProductPool productPool = new ProductPool(10);
        Productor productor = new Productor(productPool);
        Consumer consumer = new Consumer(productPool);
        productor.start();
        consumer.start();

      

    }
}
