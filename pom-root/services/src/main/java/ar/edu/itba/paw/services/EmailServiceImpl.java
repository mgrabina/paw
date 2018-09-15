package ar.edu.itba.paw.services;
import ar.edu.itba.paw.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import sun.nio.ch.sctp.SendFailed;

import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;
import java.io.File;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;

    public void sendMessage(String to, String subject, String text){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        }catch (Exception e){

        }
    }
}