package com.example.finalexam.config;

import com.example.finalexam.component.StudentProfile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 简单 Token 拦截器。
 * 规则：
 * 1. GET 请求放行，方便浏览器查看列表。
 * 2. POST、PUT、DELETE 请求必须在请求头中携带 Token。
 * 3. Token 的值必须等于 application.yml 中配置的 student.no。
 */
@Component
public class StudentTokenInterceptor implements HandlerInterceptor {

    private final StudentProfile studentProfile;

    public StudentTokenInterceptor(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String method = request.getMethod();

        if ("GET".equalsIgnoreCase(method) || "OPTIONS".equalsIgnoreCase(method)) {
            return true;
        }

        String token = request.getHeader("Token");

        if (studentProfile.getNo().equals(token)) {
            return true;
        }

        response.setStatus(401);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\":401,\"message\":\"拦截器提示：无权操作，请在请求头中携带 Token，值为你的学号。\"}");
        return false;
    }
}
