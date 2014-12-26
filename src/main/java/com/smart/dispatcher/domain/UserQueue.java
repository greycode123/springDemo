package com.smart.dispatcher.domain;

import java.util.Date;

public class UserQueue {
    private Integer ID;
    private String userName;
    private Character status;
    private Date lastDispatchTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserQueue userQueue = (UserQueue) o;

        if (!ID.equals(userQueue.ID)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public String toString() {
        return "UserQueue{" +
                "ID=" + ID +
                ", userName='" + userName + '\'' +
                ", status=" + status +
                ", lastDispatchTime=" + lastDispatchTime +
                '}';
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Date getLastDispatchTime() {
        return lastDispatchTime;
    }

    public void setLastDispatchTime(Date lastDispatchTime) {
        this.lastDispatchTime = lastDispatchTime;
    }

}
