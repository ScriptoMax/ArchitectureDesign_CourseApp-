package com.ahackannection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ahackannection.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
@ComponentScan({"com.ahackannection.resource, com.ahackannection.service, com.ahackannection.serviceimpl, com.ahackannection.repository, "
		+ "com.ahackannection.entity"})
@EnableJpaRepositories(basePackages = "com.ahackannection.repository")
public class HackannectionApplication {
	
	//@Autowired
	//final UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(HackannectionApplication.class, args);		
		
	}
	
	//@EventListener(ApplicationReadyEvent.class)	
	//private void testJpaMethods() {
			
		
	//}

}
