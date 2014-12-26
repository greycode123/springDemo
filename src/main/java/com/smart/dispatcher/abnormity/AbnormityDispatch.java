package com.smart.dispatcher.abnormity;

import com.smart.common.dispatcher.core.AutoRefreshQueueDispatcherTemplate;
import com.smart.config.service.ConfigService;
import com.smart.common.dispatcher.queue.task.QueryTaskQueue;
import com.smart.common.dispatcher.queue.user.QueryUserQueue;
import com.smart.dispatcher.service.TaskDispatcherLogService;
import com.smart.task.domain.Task;
import com.smart.task.service.TaskService;
import com.smart.user.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("abnormityDispatch")
public class AbnormityDispatch extends AutoRefreshQueueDispatcherTemplate {

    private Log log = LogFactory.getLog(AbnormityDispatch.class);

    @Resource(name = "taskService")
    private TaskService taskService;

    @Resource(name = "taskDispatcherLogService")
    private TaskDispatcherLogService taskDispatcherLogService;

    @Autowired
    private ConfigService configService;

    public AbnormityDispatch() {
        dispatcherName = "abnormityDispatch";
    }

    @Override
    public boolean canDispatch() {
        if (!configService.getValueBoolean("isRunDispatchJobFlag")) {
            log.info(dispatcherName + ":isRunDispatchJobFlag开关已关闭，不运行派单d");
            return false;
        }
        return true;
    }

    @Override
    public boolean dispatch(Task task, User user) {
        log.debug(dispatcherName + ":" + task + "d开始派单给" + user);
        taskService.fixTask(task.getTaskID(), user);
        taskDispatcherLogService.writeLog(task, user);
        log.info(dispatcherName + ":" + task + "派单给" + user + "完成");
        return false;
    }

    @Resource(name = "abnormityQueryTaskQueue")
    public void setQueryTaskQueueForAutoWare(QueryTaskQueue queryTaskQueue) {
        super.setQueryTaskQueue(queryTaskQueue);
    }

    @Resource(name = "abnormityQueryUserQueue")
    public void setQueryUserQueueForAutoWare(QueryUserQueue queryUserQueue) {
        super.setQueryUserQueue(queryUserQueue);
    }

}
