package com.alkemy.disneyapi.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

//@Service
public class EmailService {
	
	//Agregar campos en application.properties
//	@Value("${app.sendgrid.email}")
	private String emailSender;
//	@Value("${app.sendgrid.key}")
	String apiKey;
	
	public void sendWelcomeEmailTo(String to) {
		
		Email fromEmail = new Email(emailSender);
		Email toEmail = new Email(to);
		Content content = new Content(
				"text/plain",
				"Welcome to Disney API."				
				);
		
		String subject = "Esteban Pisani Disney API";
		
		Mail mail = new Mail(fromEmail, subject, toEmail, content);
		
		SendGrid sg = new SendGrid(apiKey);
		Request request = new Request();
		
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
		} catch (IOException e) {
			System.out.println("Error trying to send the email.");
		}
		
		
		
		
		
		
		
		
		
	};

}
