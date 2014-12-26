package com.smart.task.service;

import com.smart.task.dao.TaskDao;
import com.smart.task.domain.Task;
import com.smart.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("taskService")
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    public List<Task> searchTaskbyTaskStatus(int taskStatus) {
        return taskDao.searchTaskbyTaskStatus(taskStatus);
    }

    public List<Task> searchUnockTask() {
        return searchTaskbyTaskStatus(1);
    }

    public boolean fixTask(int taskID, User user) {
        return this.fixTask(taskID, user, 2);
    }

    public boolean fixTask(int taskID, User user, int taskStatus) {
        Task task = new Task();
        task.setTaskID(taskID);
        task.setTaskFixUser(user.getUserName());
        task.setTaskFixTime(new Date());
        task.setTaskStatus(taskStatus);

        int updateRowCount = this.taskDao.update(task);
        if (updateRowCount > 0) {
            return true;
        }
        return false;
    }

    public void addTask(Task task) {
        taskDao.insert(task);
    }
}
