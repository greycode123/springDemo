package com.smart.dispatcher.job;

import com.smart.dispatcher.question.QuestionDispatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("questionDispatchJob")
public class QuestionDispatchJob {

    @Resource(name = "questionDispatch")
    private QuestionDispatch questionDispatch;

    private Log log = LogFactory.getLog(QuestionDispatchJob.class);

    @Scheduled(cron = "0/5 * * * * ?")
    @Async
    public void execute() {
        log.info(questionDispatch.getDispatcherName() + ":job execute");
        long startTime = System.currentTimeMillis();
        questionDispatch.getTaskQueue().refreshTaskQueue();
        questionDispatch.dispatch();
        long endTime = System.currentTimeMillis();
        log.info(questionDispatch.getDispatcherName() + ":一轮派单花费" + (endTime - startTime) + "ms");
    }
}
