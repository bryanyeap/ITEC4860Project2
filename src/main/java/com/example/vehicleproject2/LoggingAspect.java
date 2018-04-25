package com.example.vehicleproject2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.example.vehicleproject2.*.*(..))")
    public void publicMethod() {}

    @Pointcut("execution(* com.example.vehicleproject2.*.*(..))")
    public void methodsInExamplesPackage() {}

    //@Before("publicMethod() && methodsInExamplesPackage()")

    @Before("publicMethod()")
    public void addLog( final JoinPoint joinPoint ) {
        System.out.println("Log: XExecuting: "+joinPoint.getSignature());

        Object[] arguments = joinPoint.getArgs();
        for (Object argument : arguments) {
            if (argument != null) {
                System.out.println("Log: " + argument.getClass().getSimpleName() + " = " + argument);
            }
        }
    }

}
