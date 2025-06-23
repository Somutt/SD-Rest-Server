package dev.samuel.yugigo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class YugigoApplication {

	public static void main(String[] args) {
		SpringApplication.run(YugigoApplication.class, args);
	}

}
