package by.school.diary.config;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class CommonJoinPointConfig {
    @Pointcut("execution(* by.school.diary.repository.*.*(..))")
    public void dataLayerExecution() {
    }

    @Pointcut("execution(* by.school.diary.service.*.*(..))")
    public void businessLayerExecution() {
    }

    @Pointcut("execution(* by.school.diary.controller.*.*(..))")
    public void controllerLayerExecution() {
    }
}
