/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ouzerakias.app.common;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 *
 * @author adimopo
 */

@Component
public class UtilityClass {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String recipientEmail, String token) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			
			String link = "http://49.13.24.108/token/"+token;

			helper.setFrom("dimopthan@gmail.com", "Ouzerakias Support");
			helper.setTo(recipientEmail);

			String subject = "Here's the link to confirm your account";

			String content = "<p>Hello,</p>"
					+ "<p>You have requested to create a new account.</p>"
					+ "<p>Click the link below to create a new account:</p>"
					+ "<p><a href=\"" + link + "\">Create your account</a></p>"
					+ "<br>"
					+ "<p>Ignore this email if you have not made the request</p>";
				

			helper.setSubject(subject);

			helper.setText(content, true);

			mailSender.send(message);
		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public String convertToBase64 (byte [] image){
		byte[] imageData = image;

        // Convert byte array to Base64 encoded string
        String base64Encoded = Base64.getEncoder().encodeToString(imageData);

        System.out.println(base64Encoded);
		
		return base64Encoded;
	}
}
