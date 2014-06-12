/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.potatoni.listener;

import com.potatoni.emailentity.EmailEntityComplexType;
import java.io.StringReader;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

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
    public void onMessage(javax.jms.Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            JAXBContext jc = JAXBContext.newInstance("com.potatoni.emailentity");
            Unmarshaller um = jc.createUnmarshaller();
            JAXBElement<EmailEntityComplexType> root = um.unmarshal(new StreamSource(new StringReader(tm.getText())), EmailEntityComplexType.class);
            //EmailEntityComplexType emailEntity = (EmailEntityComplexType) um.unmarshal(new StringReader(tm.getText()));
            EmailEntityComplexType emailEntity = root.getValue();
            
            sendEmail(emailEntity.getMailto(), emailEntity.getSubject(), emailEntity.getMessage());
        } catch (JAXBException ex) {
            Logger.getLogger(EmailMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(EmailMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AddressException ex) {
            Logger.getLogger(EmailMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Resource(name = "mail/smtpserver")
    private Session mailSession;
    
    public void sendEmail(String mailto, String subject, String body) throws AddressException, MessagingException {
        MimeMessage message = new MimeMessage(mailSession);
        
        InternetAddress address = new InternetAddress(mailto);
        message.setRecipient(Message.RecipientType.TO, address);
        message.setSubject(subject);
        message.setSentDate(new Date());
        message.setText(body);
        message.saveChanges();
        
        Transport tr = mailSession.getTransport();
        String serverPassword = mailSession.getProperty("mail.password");
        tr.connect(null, serverPassword);
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
    }
}