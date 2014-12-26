package com.smart.dispatcher.dao;

import com.smart.AbstractBaseTest;
import com.smart.dispatcher.domain.UserQueue;
import com.smart.user.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserQueueDaoTest extends AbstractBaseTest {

    @Autowired
    private UserQueueDao userQueueDao;

    @Test
    public void searchALLUserQueue(){
        List<User> userQueueList =  userQueueDao.getDispatcherUser();
        System.out.println(userQueueList.get(0));
        Assert.assertEquals(3, userQueueList.size());
    }
}
