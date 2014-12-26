package com.smart.jms;

import org.apache.log4j.Logger;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ReceiveJmsMessage implements MessageListener {

    private Logger log = Logger.getLogger(getClass());

    public void onMessage(Message message) {
        TextMessage textMsg = (TextMessage) message;
        try {
            log.info("receive JMS message :" + textMsg.getText());
        } catch (JMSException e) {
            log.error(e.getMessage());
            throw JmsUtils.convertJmsAccessException(e);
        }
    }
}
