package com.inn.cafe.com.inn.cafe.Utils;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailUtils {
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to , String subject, String text, List<String> list){
        SimpleMailMessage message = new SimpleMailMessage ();
        message.setFrom ("btechdays.car@gmail.com");
        message.setTo (to);
        message.setSubject (subject);
        message.setText (text);

        if(list != null && list.size () > 0){
            message.setCc(getCcArray (list));
            emailSender.send (message);


        }



    }

    private String[] getCcArray(List<String> ccList){
        String[] cc = new String[ccList.size ()];
        for(int i=0; i<ccList.size (); i++){
            cc[i] = ccList.get (i);
        }
         return cc;
    }
    public void forgotMail(String to, String Subject,String password)throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage ();
        MimeMessageHelper helper = new MimeMessageHelper (message,true);
        helper.setFrom("gaikwadgayatri308@gmail.com");
        helper.setTo(to);
        
        helper.setSubject(Subject);

    }
}
