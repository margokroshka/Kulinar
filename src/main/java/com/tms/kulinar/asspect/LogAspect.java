package com.tms.kulinar.asspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Aspect
@Component
public class  LogAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("within(com.tms.kulinar.service.UsersService)")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.info("THIS LOG BEFORE METHOD!" + joinPoint.getSignature());
    }

    @After("within(com.tms.kulinar.service.UsersService)")
    public void logAfterMethod() {
        log.info("THIS LOG AFTER METHOD!");
    }

    @AfterReturning("within(com.tms.kulinar.service.UsersService)")
    public void logAfterReturningMethod() {
        log.info("THIS LOG AFTER RETURNING METHOD!");
    }

    @AfterThrowing("within(com.tms.kulinar.service.UsersService)")
    public void logAfterThrowingMethod() {
        log.info("THIS LOG AFTER Throwing METHOD!");
    }

    @Around("@annotation(com.tms.kulinar.annotation.CheckTimeAnnotation)")
    public void logAfterAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        System.out.println("Timer start...");
        joinPoint.proceed();
        LocalTime end = LocalTime.now();
        System.out.println("Timer end...");
        log.info(String.valueOf(end.getNano() - start.getNano()));
    }
}