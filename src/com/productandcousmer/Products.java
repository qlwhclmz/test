package com.productandcousmer;


/**
 * 生产者和消费者模式
 * 产品类
 */
public class Products {

    //产品名称
    private String name;

    public Products(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
