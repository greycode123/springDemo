package com.smart.dispatcher.factoryBean;

import com.smart.common.dispatcher.core.Dispatcher;
import com.smart.common.dispatcher.core.SimpleDispatcherTemplate;
import org.springframework.beans.factory.FactoryBean;

public class DefaultDiapatcherFactoryBean implements FactoryBean<Dispatcher> {

    @Override
    public Dispatcher getObject() throws Exception {
        SimpleDispatcherTemplate simpleDispatcherTemplate = new SimpleDispatcherTemplate();
        return simpleDispatcherTemplate;
    }

    @Override
    public Class<?> getObjectType() {
        return SimpleDispatcherTemplate.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
