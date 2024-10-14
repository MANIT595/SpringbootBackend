package com.manikanta.microservices.project.UserService;


import com.manikanta.microservices.project.UserService.Service.Implementation.UserServiceImplementation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
//    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
//
//    // Pointcut for any method annotated with @Cacheable
//    @Pointcut("@annotation(Cacheable)")
//    public void cacheableMethods(Cacheable Cacheable) {}
//
//    // Before Advice
//    @Before("cacheableMethods(cacheable)")
//    public void beforeCacheableMethod(JoinPoint joinPoint, Cacheable cacheable) {
//        logger.info("Before executing method (before cacheable): " + joinPoint.getSignature().getName());
//        logger.info("Target cache names: " + String.join(", ", cacheable.value()));
//        Object[] args = joinPoint.getArgs();
//        for (Object arg : args) {
//            logger.info("Argument passed: " + arg);
//        }
//    }
//
//    // After Advice
//    @After("cacheableMethods(cacheable)")
//    public void afterCacheableMethod(JoinPoint joinPoint, Cacheable cacheable) {
//        logger.info("After executing method (after cacheable): " + joinPoint.getSignature().getName());
//    }

//    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
//
//    // Pointcut for getUserById method
//    @Pointcut("execution(* com.manikanta.microservices.project.UserService.Controller.UserController.getUserById(..))")
//    public void getUserByIdMethod() {}
//
//    // Before Advice
//    @Before("getUserByIdMethod()")
//    public void beforeGetUserById(JoinPoint joinPoint) {
//        logger.info("Before executing method: " + joinPoint.getSignature().getName());
//        Object[] args = joinPoint.getArgs();
//        for (Object arg : args) {
//            logger.info("Argument passed: " + arg);
//        }
//    }
//
//    // After Advice
//    @After("getUserByIdMethod()")
//    public void afterGetUserById(JoinPoint joinPoint) {
//        logger.info("After executing method: " + joinPoint.getSignature().getName());
//    }
}


