package com.smart.common.dispatcher.queue.user;

import com.smart.user.domain.User;

import java.util.concurrent.LinkedBlockingQueue;

public class LoopUserQueue extends AbstractUserBlockingQueue implements UserQueue {

    public LoopUserQueue() {
        this.queue = new LinkedBlockingQueue<User>();
    }

    @Override
    public User getNextUser() {
        User user = null;

        try {
            synchronized (lock){
                user = this.queue.take();
                this.queue.put(user);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return user;
    }

}
