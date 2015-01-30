package sombra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    JavaMailSender mailSender;

    public void sendMail(String to, String subject, String msg) throws MessagingException {
            MimeMessage message = mailSender.createMimeMessage();
            message.setText(msg, "UTF-8");
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            mailSender.send(message);
    }
}
