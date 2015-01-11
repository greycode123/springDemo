package com.smart.dispatcher.biz;

import com.smart.task.domain.Task;
import com.smart.task.service.TaskService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@Component("taskGenerate")
public class taskGenerate implements InitializingBean, DisposableBean{

    @Resource(name= "taskService")
    private TaskService taskService;

    private ExecutorService executorService;

    @Override
    public void afterPropertiesSet() throws Exception {

        executorService = Executors.newCachedThreadPool();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    Task task = new Task();
                    task.setTaskStatus(1);
                    taskService.addTask(task);
                    try {
                        Thread.sleep((long) Math.random() * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                while (true){
                    Task task = new Task();
                    task.setTaskStatus(1);
                    taskService.addTask(task);
                    try {
                        Thread.sleep((long) Math.random() * 500 + 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        executorService.execute(runnable);
        executorService.execute(runnable2);
    }

    @Override
    public void destroy() throws Exception {
        executorService.shutdown();
    }
}
