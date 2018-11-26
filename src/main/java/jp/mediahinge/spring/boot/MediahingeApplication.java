package jp.mediahinge.spring.boot;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MediahingeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediahingeApplication.class, args);
		Connect_to_Cloudant CtC = new Connect_to_Cloudant();
		CtC.Connect_to_Database();
	}
}
