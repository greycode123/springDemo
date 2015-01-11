package com.smart.dispatcher.biz.question;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("questionDispatchJob")
public class QuestionDispatchJob {

    @Resource(name = "questionDispatcher")
    private QuestionDispatcher questionDispatcher;

    private Log log = LogFactory.getLog(QuestionDispatchJob.class);

    @Scheduled(cron = "0/5 * * * * ?")
    @Async
    public void execute() {
        log.info(questionDispatcher.getDispatcherName() + ":job execute");
        long startTime = System.currentTimeMillis();
        questionDispatcher.getTaskQueue().refreshTaskQueue();
        questionDispatcher.dispatch();
        long endTime = System.currentTimeMillis();
        log.info(questionDispatcher.getDispatcherName() + ":一轮派单花费" + (endTime - startTime) + "ms");
    }
}
