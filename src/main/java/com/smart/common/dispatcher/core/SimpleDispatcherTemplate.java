package com.smart.common.dispatcher.core;

import com.smart.task.domain.Task;
import com.smart.user.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleDispatcherTemplate extends AbstractSimpleDispatcher {

    private Log log = LogFactory.getLog(SimpleDispatcherTemplate.class);

    private static final int max_thread_num = 4;

    private AtomicInteger currentRunThreadCount = new AtomicInteger(0);

    public SimpleDispatcherTemplate() {

    }

    public void init() {
        this.userQueue.refreshUserQueue();
    }

    @Override
    public void dispatch() {
        log.debug("dispatch start execute");
        try {
            if (currentRunThreadCount.getAndIncrement() >= max_thread_num) {
                log.debug(this.getDispatcherName() + ":current user queue=" + this.userQueue.getAllUser());
                log.info(this.getDispatcherName() + ":已经达到最大运行线程数" + max_thread_num + "， 本轮派单不运行");
                return;
            }

            if (!canDispatch()) {
                log.debug(this.getDispatcherName() + ":不满足派单条件，不能进行派单");
                return;
            }

            log.debug(this.getDispatcherName() + ":current task queue=" + this.taskQueue.getAllTask());

            Task task = taskQueue.getNextTask();
            log.debug(this.getDispatcherName() + ":get task=" + task);
            while (task != null) {

                if (!canDispatch(task)) {
                    break;
                }

                log.debug(this.getDispatcherName() + ":current user queue=" + this.userQueue.getAllUser());
                User user = userQueue.getNextUser();
                if (user == null) {
                    log.debug(this.getDispatcherName() + ":current user is null, put task " + task + " to taskQueue");
                    taskQueue.putTask(task);
                    return;
                }

                this.dispatch(task, user);

                task = taskQueue.getNextTask();
                log.debug(this.getDispatcherName() + ":get task=" + task);
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        } finally {
            currentRunThreadCount.getAndDecrement();
            log.debug("dispatch final execute");
        }
    }

    @Override
    public boolean dispatch(Task task, User us) {
        return false;
    }
}
