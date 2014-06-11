/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.potatoni.listener;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author LinFan
 */
@MessageDriven(mappedName = "jms/sendingEmailQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class EmailMessageBean implements MessageListener {
    
    public EmailMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        System.out.println(tm);
    }
}
