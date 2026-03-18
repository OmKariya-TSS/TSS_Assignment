package com.tss.springcore.SpringCore_App;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.tss.springcore")
public class SpringCoreAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCoreAppApplication.class,args);

	}
}