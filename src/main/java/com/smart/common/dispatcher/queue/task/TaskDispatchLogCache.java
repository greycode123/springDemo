package com.smart.common.dispatcher.queue.task;

import com.smart.task.domain.Task;
import org.apache.log4j.Logger;

import java.util.*;

public class TaskDispatchLogCache {

    private Logger log = Logger.getLogger(getClass());

    private List<Integer> taskDispatchLog = new ArrayList<Integer>(com.smart.common.dispatcher.queue.task.TaskQueue.DEFALUT_QUEUE_SIZE);
    private Map<Integer, Long> taskPutTaskQueueTimeMap = new HashMap<Integer, Long>(com.smart.common.dispatcher.queue.task.TaskQueue.DEFALUT_QUEUE_SIZE);

    void addTaskDispatchLog(Task task) {
        taskDispatchLog.add(task.getTaskID());
        taskPutTaskQueueTimeMap.put(task.getTaskID(), System.currentTimeMillis());
    }

    void addTaskDispatchLog(Collection<Task> tasks) {
        for (Task task : tasks) {
            addTaskDispatchLog(task);
        }
    }

    List<Task> judgeNeedRemoveTaskList(Collection<Task> tasks) {
        List<Task> needRemoveTaskList = new ArrayList<Task>();
        for (Task task : tasks) {
            if (!isExisteAndValid(task)) {
                needRemoveTaskList.add(task);
            }
        }
        return needRemoveTaskList;
    }

    boolean isExisteAndValid(Task task) {

        if (taskDispatchLog.size() > 100) {
            Integer taskID = taskDispatchLog.remove(0);
            taskPutTaskQueueTimeMap.remove(taskID);
        }

        log.debug("TaskDispatchLogCache: taskDispatchLog.size=" + taskDispatchLog.size() + " taskPutTaskQueueTimeMap.size=" + taskPutTaskQueueTimeMap.size());

        Long taskQueueTime = taskPutTaskQueueTimeMap.get(task.getTaskID());
        if (taskQueueTime != null && System.currentTimeMillis() - taskQueueTime < 1000) {
            return true;
        }

        return false;
    }

}
