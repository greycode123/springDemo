package com.smart.dispatcher.dao.impl;

import com.smart.base.dao.BaseIbatisDao;
import com.smart.common.dispatcher.queue.user.UserQueue;
import com.smart.dispatcher.dao.UserQueueDao;
import com.smart.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userQueueDao")
public class UserQueueIbatisDao extends BaseIbatisDao<UserQueue, Integer> implements UserQueueDao {

    @Override
    public List<User> getDispatcherUser() {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return super.getSqlMapClientTemplate().queryForList("GET-ALL-USER-IN-USER-QUEUE");
    }
}
