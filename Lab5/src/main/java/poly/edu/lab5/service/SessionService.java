package poly.edu.lab5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;

@Service
public class SessionService {

    @Autowired
    HttpSession session;

    // Đọc attribute trong session
    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T) session.getAttribute(name);
    }

    // Tạo hoặc thay đổi attribute
    public void set(String name, Object value) {
        session.setAttribute(name, value);
    }

    // Xóa attribute
    public void remove(String name) {
        session.removeAttribute(name);
    }
}
