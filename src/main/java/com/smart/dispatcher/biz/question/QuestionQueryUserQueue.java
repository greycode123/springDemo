package com.smart.dispatcher.biz.question;

import com.smart.common.util.ContextHelper;
import com.smart.common.dispatcher.core.QueryUserQueue;
import com.smart.dispatcher.service.UserQueueService;
import com.smart.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("questionQueryUserQueue")
public class QuestionQueryUserQueue implements QueryUserQueue {

    @Override
    public List<User> query() {
        UserQueueService userQueueService = (UserQueueService) ContextHelper.getBean("userQueueService");
        return userQueueService.getAllUser();
    }
}
