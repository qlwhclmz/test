package com.single;

/**
 * @author 79282
 * 单列模式第一种 写法:饿汉式(静态常量)
 */
public class Singleton01 {
    //私有化构造器
    private Singleton01() {

    }

    //私有静态常量
    private static final Singleton01 INSTANCE = new Singleton01();

    //get方法
    public static Singleton01 getInstance() {
        return INSTANCE;
    }

}
class Test01{
    public static void main(String[] args) {
        Singleton01 instance01 = Singleton01.getInstance();
        Singleton01 instance02 = Singleton01.getInstance();
        System.out.println(instance01 == instance02);
    }

}
