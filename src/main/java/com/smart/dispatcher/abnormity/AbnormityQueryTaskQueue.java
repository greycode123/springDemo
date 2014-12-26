package com.smart.dispatcher.abnormity;

import com.smart.common.util.ContextHelper;
import com.smart.common.dispatcher.queue.task.QueryTaskQueue;
import com.smart.task.domain.Task;
import com.smart.task.service.TaskService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("abnormityQueryTaskQueue")
public class AbnormityQueryTaskQueue implements QueryTaskQueue {

    @Override
    public List<Task> query() {
        final TaskService taskService = (TaskService) ContextHelper.getBean("taskService");
        return taskService.searchUnockTask();
    }
}