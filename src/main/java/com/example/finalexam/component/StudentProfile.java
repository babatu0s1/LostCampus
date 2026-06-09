package com.example.finalexam.component;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 学生个人信息组件。
 * 通过 @Value 读取 application.yml 中的 student.name 和 student.no。
 * 该组件会被 Service、Interceptor、Controller 注入使用。
 */



@Component
public class StudentProfile {

    @Value("${student.name}")
    private String name;

    @Value("${student.no}")
    private String no;

    public String getName() {
        return name;
    }

    public String getNo() {
        return no;
    }

    public String getLabel() {
        return name + "-" + no;
    }
}
