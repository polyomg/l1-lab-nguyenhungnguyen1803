package com.poly.lab8.controller;

import com.poly.lab8.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    MailService mailService;

    // Hiển thị form
    @GetMapping("/form")
    public String showForm() {
        return "mail/form"; // sẽ trỏ tới templates/mail/form.html
    }

    // Gửi trực tiếp
    @PostMapping("/send")
    public String sendDirect(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam(value = "cc", required = false) String cc,
            @RequestParam(value = "bcc", required = false) String bcc,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body,
            @RequestParam(value = "attachments", required = false) MultipartFile[] attachments,
            Model model
    ) throws IOException {

        String filenames = saveAttachments(attachments);
        mailService.send(MailService.Mail.builder()
                .from(from)
                .to(to)
                .cc(cc)
                .bcc(bcc)
                .subject(subject)
                .body(body)
                .filenames(filenames)
                .build());

        model.addAttribute("message", "✅ Mail đã được gửi trực tiếp!");
        return "mail/form";
    }

    // Xếp hàng đợi
    @PostMapping("/queue")
    public String sendQueue(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam(value = "cc", required = false) String cc,
            @RequestParam(value = "bcc", required = false) String bcc,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body,
            @RequestParam(value = "attachments", required = false) MultipartFile[] attachments,
            Model model
    ) throws IOException {

        String filenames = saveAttachments(attachments);
        mailService.push(MailService.Mail.builder()
                .from(from)
                .to(to)
                .cc(cc)
                .bcc(bcc)
                .subject(subject)
                .body(body)
                .filenames(filenames)
                .build());

        model.addAttribute("message", "📨 Mail đã được xếp vào hàng đợi!");
        return "mail/form";
    }

    // Hàm lưu file đính kèm tạm thời
    private String saveAttachments(MultipartFile[] attachments) throws IOException {
        if (attachments == null || attachments.length == 0) return null;
        StringBuilder sb = new StringBuilder();
        for (MultipartFile file : attachments) {
            if (!file.isEmpty()) {
                File savedFile = new File("uploads", file.getOriginalFilename());
                savedFile.getParentFile().mkdirs();
                file.transferTo(savedFile);
                sb.append(savedFile.getAbsolutePath()).append(";");
            }
        }
        return sb.toString();
    }
}
