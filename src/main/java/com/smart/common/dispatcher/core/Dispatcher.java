package com.smart.common.dispatcher.core;

import com.smart.task.domain.Task;

public interface Dispatcher {

    public void dispatch();

    String getDispatcherName();

    boolean canDispatch();

    boolean canDispatch(Task task);

}
