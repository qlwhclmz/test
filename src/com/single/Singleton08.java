package com.single;

/**
 * @author 79282
 * 使用枚举 (推荐使用)/
 */
public enum Singleton08 {

    INSTANCE;

    public void speak() {
        System.out.println("我是你爹");
    }
}
class Test08{
    public static void main(String[] args) {
        Singleton08 instance01 = Singleton08.INSTANCE;
        Singleton08 instance02 = Singleton08.INSTANCE;
        System.out.println(instance01 == instance02);
    }

}