package com.amruthacollege.welcome.utility;

import com.amruthacollege.welcome.responses.MailObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * This is the configuration mail class which uses SMTP authentication and runs
 * the service in the port 587 and gives the functionality of sending the mail
 * to user. RabbitMQ functionality added to the existing JMS mail service
 */
@Component
public class EmailServiceProvider {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendMail( MailObject mailObject ) {
        SimpleMailMessage mail = new SimpleMailMessage ();
        mail.setTo (mailObject.getEmail ());
        mail.setSubject (mailObject.getSubject ());
        mail.setText (mailObject.getMessage ());
        javaMailSender.send (mail);
    }
}