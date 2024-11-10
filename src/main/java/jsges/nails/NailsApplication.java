package jsges.nails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "jsges.nails")
public class NailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NailsApplication.class, args);
	}

}
