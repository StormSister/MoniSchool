package com.monika.monischool.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

//    @Around("execution(* com.monika.kindergarden..*.*(..))")
//    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
//        log.info(joinPoint.getSignature().toString() + " method execution start");
//        Instant start = Instant.now();
//        Object returnObj = joinPoint.proceed();
//        Instant finish = Instant.now();
//        long timeElapsed = Duration.between(start, finish).toMillis();
//        log.info("Time took to execute " + joinPoint.getSignature().toString() + " method is : "+timeElapsed);
//        log.info(joinPoint.getSignature().toString() + " method execution end");
//        return returnObj;
//    }

    @AfterThrowing(value = "execution(* com.monika.monischool.*.*(..))",throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }


}