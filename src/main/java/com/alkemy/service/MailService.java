package com.alkemy.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.springframework.stereotype.Service;


@Service
public class MailService{
    public void sendTextEmail(String to, String subject, String content) throws MessagingException{
        
      
      Properties props = new Properties();

      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");
      props.put("mail.smtp.ssl.trust", "*");

      props.put("from", "alejogalizzi@gmail.com");
      props.put("username", "alejogalizzi@gmail.com");
      props.put("password", "password");

      Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
        }
      });

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(props.getProperty("from")));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
      message.setSubject(subject);
      message.setText(content);
      Transport.send(message);
    }
    
}