package com.springboot.thread.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ExecuteAsyncTask {
    @Async
    public void executAsyncTask(Integer i){
    System.out.println("执行异步任务：----"+i);
    }
}
