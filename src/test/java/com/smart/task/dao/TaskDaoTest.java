package com.smart.task.dao;

import com.smart.AbstractBaseTest;
import com.smart.task.domain.Task;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaskDaoTest extends AbstractBaseTest {

    @Autowired
    private TaskDao taskDao;

    @Test
    public void searchTaskbyTaskStatus() {
        List<Task> taskList = taskDao.searchTaskbyTaskStatus(1);
        Assert.assertEquals(taskList.size(), 1);
    }
}
