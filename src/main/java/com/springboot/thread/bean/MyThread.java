package com.springboot.thread.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

//继承Thread类，重写run方法（其实Thread类本身也实现了Runnable接口）
public class MyThread extends Thread {

    @Override
    public void run() {
    System.out.println(Thread.currentThread().getName());
    System.out.println("多线程执行任务：是否可以传参数?"+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
       // super.run();
    }
}
