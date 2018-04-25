package com.example.vehicleproject2;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class Vehicleproject2Application {

	public static void main(String[] args) {
		SpringApplication.run(Vehicleproject2Application.class, args);
	}
}
