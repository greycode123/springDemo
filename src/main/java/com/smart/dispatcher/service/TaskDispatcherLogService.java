package com.smart.dispatcher.service;


import com.smart.base.service.BaseService;
import com.smart.dispatcher.domain.TaskDispatcherLog;
import com.smart.task.domain.Task;
import com.smart.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface TaskDispatcherLogService extends BaseService<TaskDispatcherLog, Integer> {

    public void writeLog(Task task, User user);
}
