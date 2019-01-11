package jp.mediahinge.spring.boot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MediahingeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediahingeApplication.class, args);
	}
}
