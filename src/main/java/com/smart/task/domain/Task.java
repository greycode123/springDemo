package com.smart.task.domain;

import java.util.Date;

public class Task {

	private Integer taskID;
    private int taskStatus;
    private String taskFixUser;
    private Date taskFixTime;

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskFixUser() {
        return taskFixUser;
    }

    public void setTaskFixUser(String taskFixUser) {
        this.taskFixUser = taskFixUser;
    }

    public Date getTaskFixTime() {
        return taskFixTime;
    }

    public void setTaskFixTime(Date taskFixTime) {
        this.taskFixTime = taskFixTime;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskID == null) ? 0 : taskID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (taskID == null) {
			if (other.taskID != null)
				return false;
		} else if (!taskID.equals(other.taskID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(taskID);
	}

}
