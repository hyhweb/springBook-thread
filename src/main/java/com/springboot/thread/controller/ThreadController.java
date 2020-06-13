package com.springboot.thread.controller;

import com.springboot.thread.bean.MyCallable;
import com.springboot.thread.bean.MyRunnable;
import com.springboot.thread.bean.MyRunnables;
import com.springboot.thread.bean.MyThread;
import com.springboot.thread.service.ExecuteAsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
public class ThreadController {
    //多线程的五种实现方式

    @Autowired
    private ExecuteAsyncTask executeAsyncTask;
    @GetMapping("/startAsync")
    public String startAsync() {
        for (int i = 0; i < 100; i++) {
            executeAsyncTask.executAsyncTask(i);
        }
        return Thread.currentThread().getName();
    }

  @GetMapping("/startThread")
  public String startThread() {
    for (int i = 0; i < 100; i++) {
      Thread myThread = new MyThread();
      myThread.start();
    }
    return Thread.currentThread().getName();
  }

  @GetMapping("/startRunnable")
  public String startRunnable() {
    for (int i = 0; i < 100; i++) {
      Runnable myRunnable = new MyRunnable();
      new Thread(myRunnable).start();
    }
    return Thread.currentThread().getName();
  }

    @GetMapping("/startCallable")
    public String startCallable() throws InterruptedException, ExecutionException {
        for (int i = 0; i < 100; i++) {
            Callable<Integer> mycallable = new MyCallable();
            FutureTask<Integer> futureTask = new FutureTask<>(mycallable);
            new Thread(futureTask).start();
            System.out.println(Thread.currentThread().getName() + "------" + futureTask.get());
        }
        return Thread.currentThread().getName();
    }


    /**
     * 线程池 跟数据库连接池类似 避免了线程的创建和销毁造成的额外开销
     *
     * <p>java.util.concurrent
     *
     * <p>Executor 负责现成的使用和调度的根接口 |--ExecutorService 线程池的主要接口 |--ThreadPoolExecutor 线程池的实现类
     * |--ScheduledExecutorService 接口，负责线程的调度 |--ScheduledThreadPoolExecutor (extends
     * ThreadPoolExecutor implements ScheduledExecutorService)
     *
     * <p>Executors工具类 提供了创建线程池的方法
     */
    @GetMapping("/submitExecutor")
    public String submitExecutor() {
        // 使用Executors工具类中的方法创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        MyRunnables myRunnables = new MyRunnables();
        // 为线程池中的线程分配任务,使用submit方法，传入的参数可以是Runnable的实现类，也可以是Callable的实现类
        for (int i = 0; i < 100; i++) {
            executorService.submit(myRunnables);
            System.out.println(Thread.currentThread().getName() + "------");
        }
        // 关闭线程池
        // shutdown ： 以一种平和的方式关闭线程池，在关闭线程池之前，会等待线程池中的所有的任务都结束，不在接受新任务
        // shutdownNow ： 立即关闭线程池
        executorService.shutdown();
        return Thread.currentThread().getName();
    }
}
