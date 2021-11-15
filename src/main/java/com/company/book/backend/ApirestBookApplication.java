package com.company.book.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApirestBookApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ApirestBookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "springboot";
		for (int i = 0; i < 3; i++) {
			String passwordBCrypt = passwordEncoder.encode(password);
			System.out.println(passwordBCrypt);
		}
	}/// para probar la autorizacion por token hay que ejecutar categorias y ver si en categoria por id me deja pasar una vez generado el token 
	

}
