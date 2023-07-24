package com.example.demo;
//import com.example.demo.Config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@SpringBootApplication
public class Demo3Application {
	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}

}
