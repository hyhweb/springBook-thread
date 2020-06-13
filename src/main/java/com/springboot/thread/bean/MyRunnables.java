package com.springboot.thread.bean;

public class MyRunnables implements Runnable {
    private int i =0;
    @Override
    public void run() {
        while (i<50){
            System.out.println(Thread.currentThread().getName()+"----"+i++);
        }
    }
}
