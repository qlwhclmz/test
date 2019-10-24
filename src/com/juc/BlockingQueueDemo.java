package com.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author 79282
 * 阻塞队列
当队列是空的，从队列中获取元素的操作将会被阻塞
当队列是满的，从队列中添加元素的操作将会被阻塞

试图从空的队列中获取元素的线程将会被阻塞，直到其他线程往空的队列插入新的元素
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(4);
        /**
         * add返回true 队列满时,抛出异常
         */
 /*       System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.add("d"));*/
        /**
         *  返回队列头的元素 即第一个元素
         */
       // System.out.println(blockingQueue.element());
        /**
         * remove 返回取出的元素 队列为空时抛出异常
         */
       // System.out.println(blockingQueue.remove());

        /**
         * put 队列满时,一直阻塞
         *
         */
        /*blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");*/
       // blockingQueue.put("a");
        /**
         * take 队列为空时,一直阻塞
         */

        blockingQueue.take();

    }
}
