package com.email.emailSender.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.emailSender.model.Email;
import com.email.emailSender.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
public class EmailController {

	private final EmailService emailService;

	public EmailController(EmailService emailService) {
	
		this.emailService = emailService;
	}
	
	@PostMapping("/sendMail")
	public String sendMail(@RequestBody Email email) {
		return emailService.sendEmail(email);
	}
	
	@PostMapping("/sendMailWithAttachment")
	public String sendMailWithAttachment(@ModelAttribute Email email) throws MessagingException {
		return emailService.sendMailWithAttachment(email);
	}
}
