package b8.ra291512.foundation.bank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ra291512BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ra291512BankApplication.class, args);
		System.out.println("*********");
	}
	
	 @Bean
	 public CommandLineRunner DBInit() {
		return new DBInit();		 
	 }

}
