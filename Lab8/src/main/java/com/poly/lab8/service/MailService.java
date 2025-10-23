package com.poly.lab8.service;

import lombok.Builder;
import lombok.Data;
import lombok.Builder.Default;

public interface MailService {

    @Data
    @Builder
    class Mail {
        @Default
        private String from = "WebShop <web-shop@gmail.com>";
        private String to;
        private String cc;
        private String bcc;
        private String subject;
        private String body;
        private String filenames;
    }

    // --- Gửi mail trực tiếp ---
    void send(Mail mail);

    default void send(String to, String subject, String body) {
        Mail mail = Mail.builder()
                .to(to)
                .subject(subject)
                .body(body)
                .build();
        this.send(mail);
    }

    // --- Bổ sung cho bài 2: Hàng đợi ---
    void push(Mail mail);

    default void push(String to, String subject, String body) {
        this.push(Mail.builder()
                .to(to)
                .subject(subject)
                .body(body)
                .build());
    }
}
