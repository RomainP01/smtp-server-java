package com.romain.emailSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;


@SpringBootApplication
public class EmailSpringApplication {
	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(EmailSpringApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendMail() {
		EmailReceiver emailReceiver = new EmailReceiver();
		String pop3Host = "pop.gmail.com";
		String mailStoreType = "pop3s";
		final String userName = "romainpanii@gmail.com";
		final String password = "ejavjfiipzenafow";
		List<String> tenFirstEmailSubject = emailReceiver.getTenFirstEmailSubject(pop3Host, mailStoreType, userName, password);
		System.out.println(tenFirstEmailSubject);
		emailSenderService.sendEmail("romainpanii@gmail.com", tenFirstEmailSubject.get(0), "test");

	}
}
