package com.email.emailSender.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.email.emailSender.model.Email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	private final JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;

	public EmailService(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}
	
	public String sendEmail(Email email) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			mailMessage.setFrom("Bhushan Patil<"+sender+">");
			mailMessage.setTo(email.getRecipient());
			mailMessage.setSubject(email.getSubject());
			mailMessage.setText(email.getMessage());
			
			javaMailSender.send(mailMessage);
			
			return "Email Successfully!";
			
		} catch (Exception e) {
			return "Email Sending error!";
		}
	}
	
	public String sendMailWithAttachment(Email email) throws MessagingException {
		
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		
			mimeMessageHelper.setFrom("Bhushan <"+sender+">");
			mimeMessageHelper.setTo(email.getRecipient());
			mimeMessageHelper.setSubject(email.getSubject());
			mimeMessageHelper.setText(email.getMessage());
			
			mimeMessageHelper.addAttachment(email.getGetAttachment().getOriginalFilename(), email.getGetAttachment());
		
			javaMailSender.send(mimeMessage);
			return "Email Sent Successfully";
		} catch (Exception e) {
			return "Email Sent error";
		}
	}
}
