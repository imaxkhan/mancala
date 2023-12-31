package com.bol.mancala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ConfigurationPropertiesScan("com.bol.*")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MancalaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MancalaApplication.class, args);
	}

}
