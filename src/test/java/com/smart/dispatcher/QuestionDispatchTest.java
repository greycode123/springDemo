package com.smart.dispatcher;

import com.smart.AbstractBaseTest;
import com.smart.dispatcher.abnormity.AbnormityDispatch;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class QuestionDispatchTest extends AbstractBaseTest{

    @Autowired
    private AbnormityDispatch abnormityDispatch;

    @Test
    public void dispatch(){
        abnormityDispatch.getTaskQueue().refreshTaskQueue();
        abnormityDispatch.dispatch();
    }
}
