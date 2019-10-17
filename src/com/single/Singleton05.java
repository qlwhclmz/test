package com.single;

/**
 * @author 79282
 * 懒汉式 使用同步代码块 不可用
 */
public class Singleton05 {
    private Singleton05() {

    }

    private static Singleton05 instance = null;

    public static Singleton05 getInstance() {
        if (instance == null) {
            synchronized (Singleton05.class) {
                instance = new Singleton05();
            }
        }
        return instance;
    }
}

class Test05{
    public static void main(String[] args) {

        Singleton05 instance01 = Singleton05.getInstance();
        Singleton05 instance02 = Singleton05.getInstance();
        System.out.println(instance01 == instance02);

    }
}