package com.single;

/**
 * @author 79282
 * 懒汉式 线程安全的(使用同步方法) 不推荐使用
 */
public class Singleton04 {
    //私有化构造器
    private Singleton04() {

    }

    private static Singleton04 instance = null;

    public static synchronized Singleton04 getInstance() {
        if (instance == null) {
            instance = new Singleton04();
        }
        return instance;
    }
}
class Test04{
    public static void main(String[] args) {
        Singleton04 instance01 = Singleton04.getInstance();
        Singleton04 instance02 = Singleton04.getInstance();
        System.out.println(instance01 == instance02);
    }
}
