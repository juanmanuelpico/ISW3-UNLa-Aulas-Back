package com.equipo6.aulasUnla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.proyecto.equipo6.app.configuration")
public class AulasUnlaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AulasUnlaApplication.class, args);
	}

}
