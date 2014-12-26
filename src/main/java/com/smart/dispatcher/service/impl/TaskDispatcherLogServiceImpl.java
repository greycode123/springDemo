package com.smart.dispatcher.service.impl;


import com.smart.base.service.AbstractBaseService;
import com.smart.dispatcher.dao.TaskDispatcherLogDao;
import com.smart.dispatcher.domain.TaskDispatcherLog;
import com.smart.dispatcher.service.TaskDispatcherLogService;
import com.smart.task.domain.Task;
import com.smart.user.domain.User;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("taskDispatcherLogService")
public class TaskDispatcherLogServiceImpl extends AbstractBaseService<TaskDispatcherLog, Integer> implements TaskDispatcherLogService {

    @Override
    public void writeLog(Task task, User user) {
        TaskDispatcherLog log = new TaskDispatcherLog();
        log.setTaskID(task.getTaskID());
        log.setTaskStatus(task.getTaskStatus());
        log.setUserName(user.getUserName());
        log.setDispatherTime(new Date());
        super.save(log);
    }

    @Resource(name = "taskDispatcherLogDao")
    @Required
    public void setBaseDao(TaskDispatcherLogDao taskDispatcherLogDao) {
        super.setBaseDao(taskDispatcherLogDao);
    }
}
