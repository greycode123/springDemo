package com.smart.common.dispatcher.queue.task;

import com.smart.task.domain.Task;

import java.util.List;

public interface QueryTaskQueue {
    public abstract List<Task> query();
}
