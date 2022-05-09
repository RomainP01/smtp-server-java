package com.romain.emailSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;
import java.util.Scanner;


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
		Scanner myObj = new Scanner(System.in);
		System.out.println("Bienvenue dans le programme de lecture des emails.");
		System.out.println("Vérifiez que votre adresse mail a autorisé l'accès aux applications moins sécurisées.");
		System.out.println("Veuillez entrer votre adresse mail : ");
		String userName = myObj.nextLine();
		System.out.println("Veuillez entrer votre mot de passe : ");
		String password = myObj.nextLine();
		System.out.println("Connexion à l'adresse mail : " + userName);
		List<String> tenFirstEmailSubject = emailReceiver.getTenFirstEmailSubject(pop3Host, mailStoreType, userName, password);
		System.out.println("Voici les dix premiers objets trouvés : ");
		for (String tenFirstEmailSubject1 : tenFirstEmailSubject) {
			System.out.println(tenFirstEmailSubject1);
		}
		emailSenderService.sendEmail(userName, tenFirstEmailSubject.get(0), "Voici un email de test.");

	}
}
