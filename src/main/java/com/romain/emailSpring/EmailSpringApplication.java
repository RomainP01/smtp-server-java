package com.romain.emailSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class EmailSpringApplication {
	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(EmailSpringApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void sendMail() {
		emailSenderService.sendEmail("antoinepiazza98@gmail.com", "T'es beau", "Envoie pied");

	}
}
