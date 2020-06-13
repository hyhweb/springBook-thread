package com.springboot.thread.bean;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int result =0;
    for (int i = 0; i < 10; i++) {
      result +=1;
    }
    System.out.println(Thread.currentThread().getName());
        return result;
    }
}
