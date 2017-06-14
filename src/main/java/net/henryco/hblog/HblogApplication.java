package net.henryco.hblog;

import net.henryco.hblog.configurations.MainConfiguration;
import net.henryco.hblog.configurations.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({MainConfiguration.class})
public class HblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(HblogApplication.class, args);
	}
}
