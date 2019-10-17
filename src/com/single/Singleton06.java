package com.single;

/**
 * @author 79282
 * 懒汉式 双重检验(推荐使用)
 */
public class Singleton06 {
    private Singleton06() {

    }

    private static Singleton06 instance = null;
    public static Singleton06 getInstance() {
        if (instance == null) {
            synchronized (Singleton06.class) {
                if (instance == null) {
                    instance = new Singleton06();
                }


            }
        }

        return instance;
    }
}
class Test06{
    public static void main(String[] args) {
        Singleton06 instance01 = Singleton06.getInstance();
        Singleton06 instance02 = Singleton06.getInstance();
        System.out.println(instance01 == instance02);
    }
}