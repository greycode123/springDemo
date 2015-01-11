package com.smart.dispatcher.biz.question;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("questionQueryUserJob")
public class QuestionQueryUserJob {

    @Resource(name = "questionDispatcher")
    private QuestionDispatcher questionDispatcher;

    private Log log = LogFactory.getLog(QuestionQueryUserJob.class);

    @Scheduled(cron = "0 0/1 * * * ?")
    @Async
    public void execute() {
        log.debug(questionDispatcher.getDispatcherName() + ":query user job execute");
        questionDispatcher.getUserQueue().refreshUserQueue();
        log.debug(this.questionDispatcher.getDispatcherName() + "current user queue=" + this.questionDispatcher.getUserQueue().getAllUser());
    }
}
