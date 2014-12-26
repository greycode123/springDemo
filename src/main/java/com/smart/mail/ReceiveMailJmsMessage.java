package com.smart.mail;

import org.apache.log4j.Logger;

public class ReceiveMailJmsMessage {

    private Logger log = Logger.getLogger(getClass());

    public void processMailJmsMessage(String content) {
        log.info("receive mail jms meaagse and send mail:" + content );
    }
}
