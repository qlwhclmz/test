package com.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 79282
 * 读写锁
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 10; i++) {
            int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }


        for (int i = 1; i <= 10; i++) {
            int temp = i;
            new Thread(() -> {
                myCache.get(temp + "");
            }, String.valueOf(i)).start();


        }
    }
}


class MyCache {

        private volatile Map<String, Object> map = new HashMap<>();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        /**
         * 写
         */
        public void put(String key, Object value) {
            readWriteLock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t正在写入");
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put(key, value);
                System.out.println(Thread.currentThread().getName() + "\t写入成功");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readWriteLock.writeLock().unlock();
            }

        }


        /**
         * 读
         */
        public Object get(String key) {
            readWriteLock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t正在读取");
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Object o = map.get(key);
                System.out.println(Thread.currentThread().getName() + "\t读取成功");
                return o;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();
            }

            return null;
        }


}