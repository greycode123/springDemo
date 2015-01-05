package com.smart.jms;

import com.smart.AbstractBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SendJmsMessageTest extends AbstractBaseTest {

    @Autowired
    @Qualifier("sendJmsMessageService")
    private SendJmsMessageService sendJmsMessageService;

    @Test
    public void testSend() {
        for (int i = 0; i < 2; i++) {
            sendJmsMessageService.sendMessage("你好，生产者！这是消息：" + (i + 1));
        }
    }

}
