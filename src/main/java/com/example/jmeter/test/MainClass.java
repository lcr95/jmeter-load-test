package com.example.jmeter.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainClass {
    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        Future<String> future = longRunningTask();

        //cancel future
        //future.cancel(true);

        // wait future
        String result = waitAndGet(future);
        //String result = waitAndGetWithTimeout(future);

        System.out.println(result);

        executorService.shutdown();
    }

    public static Future<String> longRunningTask() {
        return executorService.submit(() -> {
            Thread.sleep(1000);
            return "Value from future";
        });
    }

    public static String waitAndGet(Future<String> future) throws ExecutionException, InterruptedException {
        while(!future.isDone()) {
            System.out.println("Waiting future task to be completed");
            Thread.sleep(500);
        }

        return future.get();
    }

    public static String waitAndGetWithTimeout(Future<String> future) throws ExecutionException, InterruptedException, TimeoutException {
        return future.get(200, TimeUnit.MILLISECONDS);
    }


}
