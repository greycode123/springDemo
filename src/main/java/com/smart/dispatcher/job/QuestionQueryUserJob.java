package com.smart.dispatcher.job;

import com.smart.dispatcher.question.QuestionDispatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("questionQueryUserJob")
public class QuestionQueryUserJob {

    @Resource(name = "questionDispatch")
    private QuestionDispatch questionDispatch;

    private Log log = LogFactory.getLog(QuestionQueryUserJob.class);

    @Scheduled(cron = "0/1 * * * * ?")
    @Async
    public void execute() {
        log.debug(questionDispatch.getDispatcherName() + ":query user job execute");
        questionDispatch.getUserQueue().refreshUserQueue();
        log.debug(this.questionDispatch.getDispatcherName() + "current user queue=" + this.questionDispatch.getUserQueue().getAllUser());
    }
}
