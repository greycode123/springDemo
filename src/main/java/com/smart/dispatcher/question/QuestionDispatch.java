package com.smart.dispatcher.question;

import com.smart.common.dispatcher.core.SimpleDispatcherTemplate;
import com.smart.common.dispatcher.queue.task.QueryTaskQueue;
import com.smart.common.dispatcher.queue.user.LoopUserQueue;
import com.smart.common.dispatcher.queue.user.QueryUserQueue;
import com.smart.config.service.ConfigService;
import com.smart.dispatcher.service.TaskDispatcherLogService;
import com.smart.mail.MailService;
import com.smart.task.domain.Task;
import com.smart.task.service.TaskService;
import com.smart.user.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("questionDispatch")
public class QuestionDispatch extends SimpleDispatcherTemplate {

    private Log log = LogFactory.getLog(QuestionDispatch.class);

    @Resource(name = "taskService")
    private TaskService taskService;

    @Resource(name = "taskDispatcherLogService")
    private TaskDispatcherLogService taskDispatcherLogService;

    @Resource(name ="configService")
    private ConfigService configService;

    @Autowired
    private MailService mailService;

    public QuestionDispatch() {
        super();
        super.userQueue = new LoopUserQueue();
        dispatcherName = "questionDispatch";
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
        log.debug(dispatcherName + ":" + task + "开始派单给" + user);
        taskService.fixTask(task.getTaskID(), user, 3);
        taskDispatcherLogService.writeLog(task, user);
        log.info(dispatcherName + ":" + task + "派单给" + user + "完成");
        mailService.sendMail( task + "派单给" + user + "完成");
        return false;
    }

    @Resource(name = "questionQueryTaskQueue")
    public void setQueryTaskQueueForAutoWare(QueryTaskQueue queryTaskQueue) {
        super.setQueryTaskQueue(queryTaskQueue);
    }

    @Resource(name = "questionQueryUserQueue")
    public void setQueryUserQueueForAutoWare(QueryUserQueue queryUserQueue) {
        super.setQueryUserQueue(queryUserQueue);
    }
}
