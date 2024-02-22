package org.unlogged.springwebfluxdemo;

import io.unlogged.Unlogged;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebfluxDemoApplication {

	@Unlogged
	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxDemoApplication.class, args);
	}

}
