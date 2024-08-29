package com.example.acpaccounting.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(List<String> toEmails, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("info.iemsystem@gmail.com");
        message.setSubject(subject);
        message.setText(body);


        for (String toEmail : toEmails) {
            message.setTo(toEmail);
            mailSender.send(message);
        }

        System.out.println("Mail Sent Successfully...");
    }

    public String performTest() {
        return "Deneme başarılı";
    }
}
