package com.smart.task.dao;

import com.smart.base.dao.BaseIbatisDao;
import com.smart.task.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDao extends BaseIbatisDao<Task, Integer> {

    public List<Task> searchTaskbyTaskStatus(int taskStatus) {
        Task task = new Task();
        task.setTaskStatus(taskStatus);
        List<Task> taskList = this.getSqlMapClientTemplate().queryForList("searchTaskbyTaskStatus", task);
        return taskList;
    }

    public int update(Task task) {
        return this.getSqlMapClientTemplate().update("updateTask", task);
    }

    public void insert(Task task) {
        getSqlMapClientTemplate().insert("insertTask", task);
    }
}
