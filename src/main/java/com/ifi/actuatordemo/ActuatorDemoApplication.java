package com.ifi.actuatordemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActuatorDemoApplication {

	/**
	 * 공식 문서
	 * Spring Boot - https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html
	 */

	public static void main(String[] args) {
		SpringApplication.run(ActuatorDemoApplication.class, args);
	}

}
