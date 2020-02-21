package test.garbarino.martinKaynar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MartinKaynarApplication {

	public static void main(String[] args) {
		SpringApplication.run(MartinKaynarApplication.class, args);
	}

}
