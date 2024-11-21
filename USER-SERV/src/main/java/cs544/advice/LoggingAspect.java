package cs544.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // @Pointcut("execution(* cs544.services.*.*(..))")
    // public void serviceMethods() {}

    @Before("execution(* cs544.services.UserService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("### Executing method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* cs544.services.UserService.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("### Method executed: " + joinPoint.getSignature().getName() + ", Return value: " + result);
    }

    @AfterThrowing(pointcut = "execution(* cs544.services.UserService.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("### Method executed: " + joinPoint.getSignature().getName() + ", Exception: " + error);
    }
}
