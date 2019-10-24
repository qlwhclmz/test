package com.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author 79282
 * 使用futureTask获取线程的返回结果
 */
public class FutureTask01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        FutureTask futureTask = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                int i = 10;
                return i;
            }
        });

        new Thread(futureTask).start();

        Object o = futureTask.get();
        System.out.println(o);
    }
}
