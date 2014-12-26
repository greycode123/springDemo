package com.smart.common.dispatcher.queue.user;

import com.smart.user.domain.User;

import java.util.concurrent.LinkedBlockingQueue;

public class DefaultUserQueue extends AbstractUserBlockingQueue implements UserQueue {

    public DefaultUserQueue() {
        this.queue = new LinkedBlockingQueue<User>();
    }

    @Override
    public User getNextUser() {
        User user = null;

        try {
            user = this.queue.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return user;
    }

}
