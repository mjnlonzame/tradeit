package tradeit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TradeitApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeitApplication.class, args);
	}

}
