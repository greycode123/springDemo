package com.smart.dispatcher.job;

import com.smart.common.dispatcher.core.Dispatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("abnormityDispatchJob")
public class AbnormityDispatchJob {

    @Resource(name = "abnormityDispatch")
    private Dispatcher abnormityDispatch;

    private Log log = LogFactory.getLog(AbnormityDispatchJob.class);

    @Scheduled(cron =  "28,58 * * * * ?")
    @Async
    public void execute() {
        log.info(abnormityDispatch.getDispatcherName() + ":job execute");
        long startTime = System.currentTimeMillis();
        abnormityDispatch.dispatch();
        long endTime = System.currentTimeMillis();
        log.info(abnormityDispatch.getDispatcherName() + ":一轮派单花费" + (endTime - startTime) + "ms");
    }
}
