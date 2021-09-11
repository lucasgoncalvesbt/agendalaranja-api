package br.com.fcamara.hackatonapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class HackatonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackatonApiApplication.class, args);
	}

}
