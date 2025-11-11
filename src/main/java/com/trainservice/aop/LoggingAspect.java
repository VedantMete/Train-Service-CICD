package com.trainservice.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Aspect
@Component
public class LoggingAspect {
	

	    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	    // Log method execution before the actual method runs
	    @Before("execution(* com.trainservice.service.TrainService.*(..))")
	    public void logMethodEntry(JoinPoint joinPoint) {
	        logger.info("Entering method: " + joinPoint.getSignature().getName());
	    }

	    // Log method execution after the method runs
	    @After("execution(* com.trainservice.service.TrainService.*(..))")
	    public void logMethodExit(JoinPoint joinPoint) {
	        logger.info("Exiting method: " + joinPoint.getSignature().getName());
	    }

	    // Log method execution time using Around
	    @Around("execution(* com.trainservice.service.TrainService.*(..))")
	    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
	        long startTime = System.currentTimeMillis();
	        Object result = joinPoint.proceed();
	        long endTime = System.currentTimeMillis();
	        logger.info("Method " + joinPoint.getSignature().getName() + " executed in " + (endTime - startTime) + "ms");
	        return result;
	    }

	    // Log exceptions if any occur in the service methods
	    @AfterThrowing(pointcut = "execution(* com.trainservice.service.TrainService.*(..))", throwing = "ex")
	    public void logException(JoinPoint joinPoint, Throwable ex) {
	        logger.error("Exception in method: " + joinPoint.getSignature().getName() + " with message: " + ex.getMessage());
	    }
	

}

