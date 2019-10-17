package com.productandcousmer;

/**
 * @author 79282
 * 生产者
 */
public class Productor extends Thread {
    private ProductPool productPool;

    public Productor(ProductPool productPool) {
        this.productPool = productPool;
    }
    @Override
    public void run() {
        while (true) {
            String name = (int) (Math.random() * 100) + "号产品";
            System.out.println("生产了一件产品" + name);
            Products products = new Products(name);
            this.productPool.push(products);

        }
    }
}
