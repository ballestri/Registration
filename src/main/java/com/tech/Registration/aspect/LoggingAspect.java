package com.tech.Registration.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
@EnableAspectJAutoProxy
@ConditionalOnExpression("${aspect.enabled:true}")
public class LoggingAspect {

    @Around("@annotation(Timed)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        System.out.println("Class Name: " + joinPoint.getSignature().getDeclaringTypeName() + ". Method Name: " + joinPoint.getSignature().getName() + ". Time taken for Execution is : " + executionTime + " ms");

        return proceed;
    }

}
