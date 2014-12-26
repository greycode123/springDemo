package com.smart.dispatcher.dao;

import com.smart.base.dao.BaseDao;
import com.smart.common.dispatcher.queue.user.UserQueue;
import com.smart.user.domain.User;

import java.util.List;

public interface UserQueueDao extends BaseDao<UserQueue, Integer> {

    List<User> getDispatcherUser();
}
