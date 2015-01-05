package com.smart.dispatcher.service;

import com.smart.base.service.BaseService;
import com.smart.common.dispatcher.queue.user.UserQueue;
import com.smart.user.domain.User;

import java.util.List;

public interface UserQueueService extends BaseService<UserQueue, Integer> {

    List<User> getAllUser();
}
