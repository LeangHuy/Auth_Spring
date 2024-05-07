package org.example.authspring.service.serviceImpl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.example.authspring.service.EmailService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(String optCode, String email) throws MessagingException {
        Context context = new Context();
        context.setVariable("optCode", optCode);
        String process = templateEngine.process("index", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setText(process,true);
        ClassPathResource image = new ClassPathResource("images/8d4bd1e7-030a-4c91-81ef-ebe32fbc52cf.png");
        mimeMessageHelper.addInline("logo",image);
        mimeMessageHelper.setTo(email);
        javaMailSender.send(mimeMessage);
    }
}
