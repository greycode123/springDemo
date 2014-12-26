package com.smart.mail;

import com.smart.AbstractBaseTest;
import org.junit.Test;

import javax.annotation.Resource;

public class MailServiceTest extends AbstractBaseTest{

    @Resource(name = "mailService")
    private MailService mailService;

    @Test
    public void sendMail(){
        mailService.sendMail("mail test");
    }
}
