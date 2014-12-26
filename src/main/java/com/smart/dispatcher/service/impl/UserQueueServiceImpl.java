package com.smart.dispatcher.service.impl;

import com.smart.base.service.AbstractBaseService;
import com.smart.common.dispatcher.queue.user.UserQueue;
import com.smart.dispatcher.dao.UserQueueDao;
import com.smart.dispatcher.service.UserQueueService;
import com.smart.user.domain.User;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserQueueServiceImpl extends AbstractBaseService<User, Integer> implements UserQueueService {

    @Resource(name = "userQueueDao")
    @Required
    public void setUserQueueDao(UserQueueDao userQueueDao) {
        super.setBaseDao(userQueueDao);
    }
}
