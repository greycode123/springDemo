package com.smart.common.dispatcher.core;

import com.smart.common.dispatcher.queue.task.DefaultTaskQueue;
import com.smart.common.dispatcher.queue.user.DefaultUserQueue;
import com.smart.task.domain.Task;
import com.smart.user.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AutoRefreshQueueDispatcherTemplate extends AbstractSimpleDispatcher {

    private Log log = LogFactory.getLog(this.getClass());

    protected AutoRefreshQueueDispatcherTemplate() {
        userQueue = new DefaultUserQueue();
        taskQueue = new DefaultTaskQueue();
    }

    @Override
    public void dispatch() {
        if (!canDispatch()) {
            log.debug(this.getDispatcherName() + "不满足派单条件，不能进行派单");
            return;
        }

        this.getTaskQueue().refreshTaskQueue();
        log.debug(this.getDispatcherName() + ":current task queue=" + this.taskQueue.getAllTask());

        Task task = null;
        while ((task = taskQueue.getNextTask()) != null) {

            if (!canDispatch(task)) {
                break;
            }

            if (userQueue.getAllUser().size() == 0) {
                userQueue.refreshUserQueue();
                log.debug(this.getDispatcherName() + ":current user queue=" + this.userQueue.getAllUser());
            }

            User user = userQueue.getNextUser();
            this.dispatch(task, user);

            if (userQueue.getAllUser().size() == 0) {
                userQueue.refreshUserQueue();
                log.debug(this.getDispatcherName() + "current user queue=" + this.userQueue.getAllUser());
            }
        }
    }
}
