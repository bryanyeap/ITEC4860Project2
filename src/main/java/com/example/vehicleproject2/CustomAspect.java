package com.example.vehicleproject2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Aspect
@Component
public class CustomAspect {

    @PersistenceContext
    private EntityManager entityManager;

    @Pointcut("execution(* com.example.vehicleproject2.*.*(..))")
    public void customMethod() {}

    @Before("customMethod()")
    public void custom(final JoinPoint joinPoint) {
        String point = joinPoint.getSignature().toString();
        if(point.indexOf("addVehicle(") >= 0) {
            System.out.println("Custom: POST request called");
        }
        else if(point.indexOf("getVehicle(") >= 0) {
            System.out.println("Custom: GET request called");
        }
        else if(point.indexOf("removeVehicle(") >= 0) {
            System.out.println("Custon: DELETE request called");
        }
        else if(point.indexOf("updateVehicle(") >= 0) {
            System.out.println("Custom: PUT request called");
        }
        else if(point.indexOf("getLatestVehicles(") >= 0){
            System.out.println("Custom: GET request called");
        }
    }

    @Before("customMethod()")
    public void count(final JoinPoint joinPoint) {
        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM vehicles");
        System.out.println("Count: " + query.getSingleResult());
    }
}
