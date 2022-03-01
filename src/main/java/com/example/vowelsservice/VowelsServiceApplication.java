package com.example.vowelsservice;

import com.example.vowelsservice.service.VowelService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class VowelsServiceApplication {

	public static void main(String[] args) throws IOException {
		ApplicationContext ctx = SpringApplication.run(VowelsServiceApplication.class, args);
		VowelService vowelService = (VowelService) ctx.getBean("vowelService");
		vowelService.getVowels();

	}

}
