package com.smart.common.dispatcher.queue.user;

import com.smart.user.domain.User;

import java.util.Collection;
import java.util.List;

public interface UserQueue {

    public abstract User getNextUser();

    public abstract List<User> getAllUser();

    public abstract boolean putUser(User user);

    public abstract List<User> putAllUser(Collection<User> users);

    boolean removeUser(User user);

    public List<User> refreshUserQueue();

    public void setQueryUserQueue(QueryUserQueue queryUserQueue);
}