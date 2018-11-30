package jp.mediahinge.spring.boot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MediahingeApplication {

	public static void main(String[] args) {
		System.out.println("DEBUG:MediahingeApplication:01");
		SpringApplication.run(MediahingeApplication.class, args);
	}
}
