package com.springboot.thread.bean;

public class MyRunnable implements Runnable {
    private volatile  int i = 0;
    @Override
    public void run() {
    System.out.println(Thread.currentThread().getName()+"-aaaaa-"+ (i++));
    }
}
