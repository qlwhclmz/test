package com.juc;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import	java.util.concurrent.CopyOnWriteArraySet;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制解决集合不安全
 * @author 79282
 */
public class NotSafeDemo {
    public static void main(String[] args) {
        //ArrayList
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 40; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        //set
        Set<String> set = new CopyOnWriteArraySet<> ();
        for (int i = 1; i <= 40; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }

        //map
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 40; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

}
