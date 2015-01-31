package sombra.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    JavaMailSender mailSender;

    public void sendMail(String to, String subject, String msg){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            message.setText(msg, "UTF-8");
            message.setFrom(new InternetAddress("drbaxa@mail.com"));
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            mailSender.send(message);
        } catch (MessagingException | MailException messageEx) {
            logger.error(messageEx.getMessage());
            logger.debug(messageEx.getMessage(), messageEx);
        }
    }
}
