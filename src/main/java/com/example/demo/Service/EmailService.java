package com.example.demo.Service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendForgotPasswordConfirmation(String toEmail, String token) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            message.setFrom("nguyenhauxmvt@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject("Đổi mật khẩu");
            String confirmationLink = "https://fc96-2001-ee0-f1b9-1ca0-8c36-1a3d-b063-b8c8.ngrok-free.app/api/auth/reset-password?token=" + token;
            String content = "<html><body>"
                    + "<p>Chào bạn,</p>"
                    + "<p>Vui lòng thay đổi mật khẩu của bạn bằng cách nhấp vào liên kết dưới đây(Link chỉ có hiệu lực trong vòng 5 phút):</p>"
                    + "<p><a href=\"" + confirmationLink + "\">Thay đổi mật khẩu</a></p>"
                    + "<p>Trân trọng,</p>"
                    + "<p>Đội ngũ hỗ trợ</p>"
                    + "</body></html>";

            helper.setText(content, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





