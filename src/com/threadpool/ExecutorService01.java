package com.threadpool;
import java.util.concurrent.*;

/**
 * @author 79282
 *
 * 使用线程池获取线程的返回值
 *
 */
public class ExecutorService01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
                10,
                2L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy()
        );

        Future future = poolExecutor.submit( new Callable() {
            @Override
            public Object call() throws Exception {
                int i = 10;
                return i;
            }
        });
        Object o = future.get();
        System.out.println(o);
    }
}
