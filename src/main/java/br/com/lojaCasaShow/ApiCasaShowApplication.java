package br.com.lojaCasaShow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiCasaShowApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCasaShowApplication.class, args);
		//System.out.println(new BCryptPasswordEncoder().encode("asdfg"));
		System.out.println(new BCryptPasswordEncoder().encode("12345"));
		//System.out.println(new BCryptPasswordEncoder().encode("54321"));
	}

}
