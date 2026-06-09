package com.example.finalexam.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * AOP 切面。
 * 用于统计 Controller 和 Service 方法执行耗时。
 */
@Aspect
@Component
public class OperationLogAspect {

    @Pointcut("execution(* com.example.finalexam.controller..*(..))")
    public void controllerMethods() {
    }

    @Pointcut("execution(* com.example.finalexam.service..*(..))")
    public void serviceMethods() {
    }

    @Around("controllerMethods() || serviceMethods()")
    public Object logCostTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        try {
            return joinPoint.proceed();
        } finally {
            long cost = System.currentTimeMillis() - start;
            System.out.println("⏱️ [AOP耗时统计] "
                    + joinPoint.getSignature().toShortString()
                    + " 执行耗时："
                    + cost
                    + " ms");
        }
    }
}
