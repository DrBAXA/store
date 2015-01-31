package sombra.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    Environment env;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    JavaMailSender mailSender;

    public void sendMail(String to, String subject, String msg){
        Thread sender = new Thread(new MailSenderThread(to, env.getProperty("mail.from.address"), subject, msg));
        sender.start();
    }

    class MailSenderThread implements Runnable{
        private String to;
        private String from;
        private String subject;
        private String messageText;

        public MailSenderThread(String to, String from, String subject, String messageText) {
            this.to = to;
            this.from = from;
            this.subject = subject;
            this.messageText = messageText;
        }

        @Override
        public void run() {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                message.setText(messageText, "UTF-8");
                message.setFrom(new InternetAddress(from));
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
}
