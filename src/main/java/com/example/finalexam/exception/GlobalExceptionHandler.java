package com.example.finalexam.exception;

import com.example.finalexam.component.StudentProfile;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 全局异常处理器。
 * 所有 Controller 中抛出的异常，会统一在这里转换成友好的 JSON。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final StudentProfile studentProfile;

    public GlobalExceptionHandler(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    @ExceptionHandler(BusinessException.class)
    public Map<String, Object> handleBusinessException(BusinessException e, HttpServletResponse response) {
        response.setStatus(400);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("code", 400);
        result.put("message", e.getMessage());
        result.put("student", studentProfile.getLabel());
        result.put("time", LocalDateTime.now().toString());
        return result;
    }

    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception e, HttpServletResponse response) {
        response.setStatus(500);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("code", 500);
        result.put("message", "系统开了个小差，请检查控制台日志。错误信息：" + e.getMessage());
        result.put("student", studentProfile.getLabel());
        result.put("time", LocalDateTime.now().toString());
        return result;
    }
}
