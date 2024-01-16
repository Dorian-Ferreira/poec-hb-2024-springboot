package fr.poec.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = "fr.poec.springboot.courses")
@SpringBootApplication(scanBasePackages = "fr.poec.springboot.instant_faking")
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
