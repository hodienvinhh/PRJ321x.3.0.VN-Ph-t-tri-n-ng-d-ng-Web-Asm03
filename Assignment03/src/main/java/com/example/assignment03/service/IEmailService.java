package com.example.assignment03.service;

import com.itextpdf.text.DocumentException;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IEmailService {
    void sendEmail(String to, String subject, String body);

    void forgotPassword(String to);

    void confirmByDoctor(String to);

    void sendMessageWithAttachment(String to,
                                   String subject,
                                   String text,
                                   MultipartFile  file
                                  ) throws MessagingException, IOException, DocumentException;
}
