package com.tech.Registration.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Run before the method execution.
     * @param joinPoint
     */
    @Before("execution(* com.tech.Registration.service.UserService.saveUser(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.debug("logBefore running .....");
        log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

    }

    /**
     * Run after the method returned a result.
     * @param joinPoint
     */
    @After("execution(* com.tech.Registration.service.UserService.saveUser(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.debug("logAfter running .....");
        log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }


    /**
     * Run around the method execution.
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.tech.Registration.service.UserService.findByUsername(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("logAround running .....");
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }

    }


}
