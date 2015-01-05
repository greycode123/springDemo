package com.smart.common.dispatcher.queue.user;

import com.smart.common.dispatcher.core.QueryUserQueue;
import com.smart.user.domain.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class AbstractUserBlockingQueue implements UserQueue {

    private static Logger log = Logger.getLogger(AbstractUserBlockingQueue.class);

    protected BlockingQueue<User> queue;

    private QueryUserQueue queryUserQueue;

    protected final Object lock = new Object();

    public AbstractUserBlockingQueue() {
        super();
    }

    @Override
    public User getNextUser() {
        return null;
    }

    @Override
    public boolean putUser(User user) {

        boolean putflag = false;

        synchronized (lock){
            if(!this.queue.contains(user)){
                try {
                    this.queue.put(user);
                    putflag = true;
                } catch (InterruptedException e) {
                    log.warn(e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        return putflag;
    }

    @Override
    public List<User> putAllUser(Collection<User> users) {
        List<User> userList = new ArrayList<User>();

        synchronized (lock) {
            for (User user : users) {
                if (!queue.contains(user)) {
                    this.putUser(user);
                    userList.add(user);
                }
            }
        }

        return userList;
    }

    @Override
    public boolean removeUser(User user) {
        return this.queue.remove(user);
    }

    @Override
    public List<User> getAllUser() {
        User[] users = new User[0];
        return Arrays.asList(this.queue.toArray(users));
    }

    public List<User> refreshUserQueue() {
        List<User> userList = this.queryUserQueue.query();
        synchronized (lock) {
            for (User user : userList) {
                if (true) {
                    if (this.putUser(user)) {
                        //log.info(this.getDispatcherName() + ":" + user + "进入用户处理队列");
                    }
                } else {
                    if (this.removeUser(user)) {
                        //log.info(this.getDispatcherName() + ":" + user + "移除用户处理队列");
                    }
                }
            }
        }
        List<User> submitedUserList = new ArrayList<User>();
//        if(submitedUserList.size() > 0)
//            log.info(this.getDispatcherName() + ":" + submitedUserList + "进入用户处理队列");
        return submitedUserList;
    }

    public void setQueryUserQueue(QueryUserQueue queryUserQueue){
        this.queryUserQueue = queryUserQueue;
    }

}