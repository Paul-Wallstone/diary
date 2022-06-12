package by.school.diary.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
@Slf4j
public class AopAspectConfig {

    public static final String DATA_LAYER_EXECUTION = "by.school.diary.config.CommonJoinPointConfig.dataLayerExecution())";
    public static final String BUSINESS_LAYER_EXECUTION = "by.school.diary.config.CommonJoinPointConfig.businessLayerExecution())";
    public static final String CONTROLLER_LAYER_EXECUTION = "by.school.diary.config.CommonJoinPointConfig.controllerLayerExecution())";

    @Before(value = DATA_LAYER_EXECUTION)
    public void beforeDataLayer(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        log.info("Method: {}, args: {}", signature.toShortString(), joinPoint.getArgs());
    }

    @AfterReturning(value = DATA_LAYER_EXECUTION,
            returning = "result")
    public void afterDataLayerReturning(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        log.info("Method: {}, result: {}", signature.toShortString(), result);
    }

    @Before(value = BUSINESS_LAYER_EXECUTION)
    public void beforeBusinessLayer(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        log.info("Method: {}, args: {}", signature.toShortString(), joinPoint.getArgs());
    }

    @AfterReturning(value = BUSINESS_LAYER_EXECUTION,
            returning = "result")
    public void afterBusinessLayerReturning(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        log.info("Method: {}, result: {}", signature.toShortString(), result);
    }

    @Before(value = CONTROLLER_LAYER_EXECUTION)
    public void beforeControllerLayer(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        log.info("Method: {}, args: {}", signature.toShortString(), joinPoint.getArgs());
    }

    @AfterReturning(value = CONTROLLER_LAYER_EXECUTION,
            returning = "result")
    public void afterControllerLayerReturning(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        log.info("Method: {}, result: {}", signature.toShortString(), result);
    }

}
