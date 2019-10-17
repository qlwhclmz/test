package com.single;

/**
 * @author 79282
 * 懒汉式 (线程不安全)不可用
 */
public class Singleton03 {
    //私有化构造器
    private Singleton03() {

    }

    private static Singleton03 instance = null;


    public static Singleton03 getInstance() {

        if (instance == null) {
             instance = new Singleton03();
        }
        return instance;
    }

}

class Test03 {
    public static void main(String[] args) {
        Singleton03 instance01 = Singleton03.getInstance();
        Singleton03 instance02 = Singleton03.getInstance();
        System.out.println(instance01 == instance02);
    }
}