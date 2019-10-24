package com.threadpool;

import java.util.concurrent.*;

/**
 * @author 79282
 * 线程池
 * 1.为什么要使用线程池?
 * 一. 避免频繁的创建线程和销毁线程 导致资源的销毁
 * 二.提高响应速度
 * 三.提高线程的可管理性
 * <p>
 * newFixedThreadPool创建的线程池corePoolSize和maximumPoolSize值是相等的，它使用的是LinkedBlockingQueue
 * <p>
 * newSingleThreadExecutor 创建的线程池corePoolSize和maximumPoolSize值都是1，它使用的是LinkedBlockingQueue
 * <p>
 * newCachedThreadPool创建的线程池将corePoolSize设置为0，将maximumPoolSize设置为Integer.MAX_VALUE，它使用的是SynchronousQueue，也就是说来了任务就创建线程运行，当线程空闲超过60秒，就销毁线程。
 *
 *
 *
 *
 * 线程池工作原理:
 *    1、在创建了线程池后，开始等待请求。
 *   2、当调用execute()方法添加一个请求任务时，线程池会做出如下判断：
 *   2.1如果正在运行的线程数量小于corePoolSize，那么马上创建线程运行这个任务；
 *   2.2如果正在运行的线程数量大于或等于corePoolSize，那么将这个任务放入队列；
 *   2.3如果这个时候队列满了且正在运行的线程数量还小于maximumPoolSize，那么还是要创建非核心线程立刻运行这个任务；
 *   2.4如果队列满了且正在运行的线程数量大于或等于maximumPoolSize，那么线程池会启动饱和拒绝策略来执行。
 *   3、当一个线程完成任务时，它会从队列中取下一个任务来执行。
 *    4、当一个线程无事可做超过一定的时间（keepAliveTime）时，线程会判断：
 *     如果当前运行的线程数大于corePoolSize，那么这个线程就被停掉。
 *     所以线程池的所有任务完成后，它最终会收缩到corePoolSize的大小。
 */
public class ThreadPool {
    public static void main(String[] args) {
       // ExecutorService executorService = Executors.newFixedThreadPool(10);
        /**
         * 线程池7大参数:
         * 1.corePoolSize:线程池中的常驻核心线程数
         * 2.maxmumPoolSize:线程池中最多能容纳的线程数(必须大于等于1)
         * 3.keepAliveTime:多余的空闲线程存活时间
         * 4.unit:keepAliveTime的时间单位
         * 5.workQueue:任务队列,被提交但未被执行的任务
         * 6.threadFactory:表示生成线程池中工作线程的线程工厂,用于创建线程,一般默认
         * 7.handler:拒绝策略,表示当队列任务满后,并且工作线程大于等于线程池的最大线程数(maxmumpoolSize)是如何来拒绝请求执行的handler策略
         */
        ExecutorService pool = new ThreadPoolExecutor(5,
                                                 5,
                                                     5L,
                                                    TimeUnit.SECONDS,
                                                    new LinkedBlockingQueue<Runnable>(1024),
                                                    Executors.defaultThreadFactory(),
                                                    new ThreadPoolExecutor.AbortPolicy()//默认拒绝策略抛出rejectedExecutionException异常
                                                    //new ThreadPoolExecutor.CallerRunsPolicy()既不抛弃任务,也不抛出异常 将任务退回给调用者
                                                    //new ThreadPoolExecutor.DiscardOldestPolicy()抛弃队列中等待最久的任务 然后把当前任务进入到队列中,尝试再次提交当前任务
                                                  //new ThreadPoolExecutor.DiscardPolicy()默默丢弃无法处理的任务
                                                    //以上内置策略都实现了rejectedExecutionHandlerHandler接口
                                                    );
        try {
            for (int i = 1; i <= 100; i++) {
                pool.execute(() -> System.out.println(Thread.currentThread().getName()+"\t办理业务"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }



    }
}
