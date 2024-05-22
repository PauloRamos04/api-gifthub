package com.example.GiftHub.infra.email;

import com.example.GiftHub.domain.user.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmailVerificacao(User user) {
        String destinatario = user.getEmail();
        String assunto = "Verifique seu email";
        String mensagem = String.format("Clique no link para verificar seu email: http://localhost:8080/auth/verify?token=%s", user.getVerificationToken());

        enviarEmail(destinatario, assunto, mensagem);
    }

    private void enviarEmail(String destinatario, String assunto, String mensagem) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(mensagem, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Falha ao enviar email", e);
        }
    }
}
