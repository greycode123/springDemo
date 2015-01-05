package com.smart.common.dispatcher.core;

import com.smart.common.dispatcher.queue.task.TaskQueue;
import com.smart.common.dispatcher.queue.user.UserQueue;
import com.smart.task.domain.Task;
import com.smart.user.domain.User;

public abstract class AbstractSimpleDispatcher implements Dispatcher {

    protected String dispatcherName;
    protected UserQueue userQueue;
    protected TaskQueue taskQueue;

    public boolean canDispatch() {
        return true;
    }

    public boolean canDispatch(Task task) {
        return true;
    }

    public abstract boolean dispatch(Task task, User us);

    public void setQueryTaskQueue(QueryTaskQueue queryTaskQueue) {
        this.taskQueue.setQueryTaskQueue(queryTaskQueue);
    }

    public void setQueryUserQueue(QueryUserQueue queryUserQueue) {
        this.userQueue.setQueryUserQueue(queryUserQueue);
    }

    @Override
    public int hashCode() {
        return dispatcherName != null ? dispatcherName.hashCode() : 0;
    }

    public String getDispatcherName() {
        return dispatcherName;
    }

    public void setDispatcherName(String dispatcherName) {
        this.dispatcherName = dispatcherName;
    }

    public UserQueue getUserQueue() {
        return userQueue;
    }

    public void setUserQueue(UserQueue userQueue) {
        this.userQueue = userQueue;
    }

    public TaskQueue getTaskQueue() {
        return taskQueue;
    }

    public void setTaskQueue(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }
}
