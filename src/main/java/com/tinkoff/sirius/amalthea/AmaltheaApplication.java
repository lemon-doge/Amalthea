package com.tinkoff.sirius.amalthea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class})
public class AmaltheaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmaltheaApplication.class, args);
	}

}
