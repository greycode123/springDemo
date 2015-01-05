package com.smart.common.dispatcher.queue.task;

import com.smart.common.dispatcher.core.QueryTaskQueue;
import com.smart.task.domain.Task;

import java.util.Collection;
import java.util.List;

public interface TaskQueue {

    int DEFALUT_QUEUE_SIZE = 1000;

    public abstract Task getNextTask();

    public abstract boolean putTask(Task task);

    public abstract List<Task> putAllTask(Collection<Task> tasks);

    public abstract List<Task> getAllTask();

    int getSize();

    void removeFromDoingTaskList(Task task);

    public List<Task> refreshTaskQueue();

    public void setQueryTaskQueue(QueryTaskQueue queryTaskQueue);
}