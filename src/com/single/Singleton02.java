package com.single;

/**
 * @author 79282
 * 饿汉式 静态代码块
 */
public class Singleton02 {
    //私有化构造器
    private Singleton02() {

    }

    private static Singleton02 instance;
    //静态代码块
    static{
        instance = new Singleton02();
    }

    public static Singleton02 getInstance() {
        return instance;
    }
}
class Test02{

    public static void main(String[] args) {
        Singleton02 instance01 = Singleton02.getInstance();
        Singleton02 instance02 = Singleton02.getInstance();
        System.out.println(instance01 == instance02);
    }
}
