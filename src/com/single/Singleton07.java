package com.single;

/**
 * @author 79282
 * 静态内部类创建单例(推荐使用)
 */
public class Singleton07 {
    private Singleton07() {


    }

    //私有静态内部类
    private static class SingletonInstance{
        private static final Singleton07 INSTANCE = new Singleton07();
    }

    public static Singleton07 getInstance() {
        return  SingletonInstance.INSTANCE;
    }


}
class Test07{
    public static void main(String[] args) {

        Singleton07 instance01 = Singleton07.getInstance();
        Singleton07 instance02 = Singleton07.getInstance();
        System.out.println(instance01 == instance02);
    }
}