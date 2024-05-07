package org.example.authspring.service;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendMail(String optCode,String email) throws MessagingException;
}
