package com.poly.lab8.service.impl;

import com.poly.lab8.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;

    // Hàng đợi lưu email chờ gửi
    List<Mail> queue = new ArrayList<>();

    // --- Gửi mail trực tiếp ---
    @Override
    public void send(Mail mail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            // 2.1. Người gửi
            helper.setFrom(mail.getFrom());
            helper.setReplyTo(mail.getFrom());

            // 2.2. Người nhận
            helper.setTo(mail.getTo());
            if (!isNullOrEmpty(mail.getCc())) helper.setCc(mail.getCc());
            if (!isNullOrEmpty(mail.getBcc())) helper.setBcc(mail.getBcc());

            // 2.3. Tiêu đề và nội dung
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(), true);

            // 2.4. File đính kèm (nếu có)
            String filenames = mail.getFilenames();
            if (!isNullOrEmpty(filenames)) {
                for (String filename : filenames.split("[,;]+")) {
                    File file = new File(filename.trim());
                    if (file.exists()) {
                        helper.addAttachment(file.getName(), file);
                    }
                }
            }

            // 3. Gửi Mail
            mailSender.send(message);

            System.out.println("✅ Đã gửi mail tới: " + mail.getTo());

        } catch (Exception e) {
            System.err.println("❌ Lỗi khi gửi mail: " + e.getMessage());
        }
    }

    // --- Thêm vào hàng đợi ---
    @Override
    public void push(Mail mail) {
        queue.add(mail);
        System.out.println("📨 Đã thêm mail vào hàng đợi: " + mail.getTo());
    }

    // --- Chạy nền để gửi mail trong hàng đợi ---
    @Scheduled(fixedDelay = 500)
    public void run() {
        while (!queue.isEmpty()) {
            try {
                Mail mail = queue.remove(0);
                this.send(mail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isNullOrEmpty(String text) {
        return (text == null || text.trim().isEmpty());
    }
}
