package com.smart.mail.impl;

import com.smart.jms.SendJmsMessageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;

@Component("mailService")
public class MailServiceJmsImpl implements com.smart.mail.MailService {

    private Logger log = Logger.getLogger(getClass());

    @Resource(name = "sendJmsMessageService")
    private SendJmsMessageService sendJmsMessageService;

    @Resource(name = "mailJmsQueue")
    private Destination destination;

    @Override
    public void sendMail(String content) {
        sendJmsMessageService.sendMessage(content, destination);
    }
}
