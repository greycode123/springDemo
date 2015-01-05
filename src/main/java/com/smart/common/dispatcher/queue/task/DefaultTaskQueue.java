package com.smart.common.dispatcher.queue.task;

import com.smart.common.dispatcher.core.QueryTaskQueue;
import com.smart.task.domain.Task;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DefaultTaskQueue implements TaskQueue {
    private static Logger log = Logger.getLogger(DefaultTaskQueue.class);

    protected BlockingQueue<Task> queue;
    protected List<Task> doingTaskList = Collections.synchronizedList(new ArrayList<Task>());
    protected QueryTaskQueue queryTaskQueue;

    public DefaultTaskQueue() {
        this.queue = new ArrayBlockingQueue<Task>(TaskQueue.DEFALUT_QUEUE_SIZE);
    }

    @Override
    public Task getNextTask() {
        Task task = queue.poll();
        doingTaskList.add(task);
        return task;
    }

    @Override
    public boolean putTask(Task task) {
        if (!this.queue.contains(task) && !doingTaskList.contains(task)) {
            this.queue.offer(task);
            return true;
        }

        return false;
    }

    @Override
    public List<Task> putAllTask(Collection<Task> tasks) {
        List<Task> taskList = new ArrayList<Task>();

        for (Task task : tasks) {
            if (putTask(task))
                taskList.add(task);
        }

        return taskList;
    }

    @Override
    public List<Task> getAllTask() {
        Task[] tasks = new Task[0];
        return Arrays.asList(this.queue.toArray(tasks));
    }

    @Override
    public int getSize() {
        return this.queue.size();
    }

    @Override
    public List<Task> refreshTaskQueue(){
        List<Task> taskList =  this.queryTaskQueue.query();
        //log.debug(dispatcherName + ":" + needRemoveTaskList + "被taskDispatchLogCache认为不能进入待派单队列");

        List<Task> submitedTaskList =  this.putAllTask(taskList);
        if(submitedTaskList.size() > 0){
            //log.info(dispatcherName + ":" + submitedTaskList + "进入待派单队列");
        }

        return submitedTaskList;
    }

    @Override
    public void removeFromDoingTaskList(Task task) {
        if (doingTaskList.size() > 100) {
            doingTaskList.remove(0);
        }
    }

    public void setQueryTaskQueue(QueryTaskQueue queryTaskQueue) {
        this.queryTaskQueue = queryTaskQueue;
    }
}
