package com.bupt.battery.task;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

@Component
public class TaskThreadPoolExecutor<T extends Runnable> {
    private volatile static ThreadPoolExecutor taskPool;

    private Integer coreSize= 60;
    private Integer idleTime=60;
    public TaskThreadPoolExecutor()
    {
        if(taskPool==null)
        {
            synchronized (TaskThreadPoolExecutor.class)
            {
                if(taskPool==null)
                {
                    taskPool=new ThreadPoolExecutor(coreSize,coreSize,idleTime, TimeUnit.SECONDS,new LinkedBlockingQueue<>(),new TaskThreadFactory());
                }
            }
        }
    }
    public void execute(T task)
    {
        taskPool.execute(task);
    }
}
