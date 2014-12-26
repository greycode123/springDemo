package com.smart.dispatcher.dao.impl;

import com.smart.base.dao.BaseIbatisDao;
import com.smart.dispatcher.dao.TaskDispatcherLogDao;
import com.smart.dispatcher.domain.TaskDispatcherLog;
import org.springframework.stereotype.Repository;

@Repository("taskDispatcherLogDao")
public class TaskDispatcherLogIbatisDao extends BaseIbatisDao<TaskDispatcherLog, Integer> implements TaskDispatcherLogDao {
}
