package com.smart.dispatcher.biz.question;

import com.smart.common.util.ContextHelper;
import com.smart.common.dispatcher.core.QueryTaskQueue;
import com.smart.task.domain.Task;
import com.smart.task.service.TaskService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("questionQueryTaskQueue")
public class QuestionQueryTaskQueue implements QueryTaskQueue {

    @Override
    public List<Task> query() {
        final TaskService taskService = (TaskService) ContextHelper.getBean("taskService");
        return taskService.searchTaskbyTaskStatus(2);
    }
}
