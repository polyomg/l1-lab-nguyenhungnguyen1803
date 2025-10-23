package com.poly.lab8.interceptor;

import com.poly.lab8.entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String uri = request.getRequestURI();
        session.setAttribute("securityUri", uri);

        Account user = (Account) session.getAttribute("user");

        // Nếu chưa đăng nhập
        if (user == null) {
            session.setAttribute("message", "Vui lòng đăng nhập để tiếp tục!");
            response.sendRedirect("/auth/login");
            return false;
        }

        // Nếu không phải admin mà truy cập trang admin
        if (uri.startsWith("/admin") && !user.isAdmin()) {
            session.setAttribute("message", "Bạn không có quyền truy cập trang quản trị!");
            response.sendRedirect("/auth/login");
            return false;
        }

        return true;
    }
}
