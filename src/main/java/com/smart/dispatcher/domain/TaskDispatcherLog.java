package com.smart.dispatcher.domain;

import java.io.Serializable;
import java.util.Date;

public class TaskDispatcherLog implements Serializable{
    private Integer ID;
    private Integer taskID;
    private Integer taskStatus;
    private String userName;
    private Date dispatherTime;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDispatherTime() {
        return dispatherTime;
    }

    public void setDispatherTime(Date dispatherTime) {
        this.dispatherTime = dispatherTime;
    }
}
