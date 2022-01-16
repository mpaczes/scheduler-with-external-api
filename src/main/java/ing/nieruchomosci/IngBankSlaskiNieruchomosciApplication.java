package ing.nieruchomosci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IngBankSlaskiNieruchomosciApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngBankSlaskiNieruchomosciApplication.class, args);
	}

}
