package com.smart.common.dispatcher.queue.user;

import com.smart.user.domain.User;

import java.util.List;

public interface QueryUserQueue {

    List<User> query();
}
