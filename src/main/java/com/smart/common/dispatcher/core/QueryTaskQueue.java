package com.smart.common.dispatcher.core;

import com.smart.task.domain.Task;

import java.util.List;

public interface QueryTaskQueue {
    public abstract List<Task> query();
}
