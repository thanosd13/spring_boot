package com.ouzerakias.app.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.ouzerakias.app.entity.UserDao;
import com.ouzerakias.app.service.MailService;
import com.ouzerakias.app.service.UserService;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author adimopo
 */
@RestController
@RequestMapping("/forgot_pass")
public class ForgotPasswordController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailService mailService;

	@Autowired
	private UserService userService;

	@GetMapping("/sendTestEmail")
	public String sendTestEmail() {
		mailService.sendTestEmail();
		return "Test email sent";
	}

	@PostMapping("/forgot_password")
	public ResponseEntity<String> processForgotPassword(HttpServletRequest request) {
		String email = request.getParameter("email");
		String token = RandomString.make(30);

		try {
			userService.updateResetPasswordToken(token, email);
			String resetPasswordLink = "http://49.13.24.108/create_new_pass/" + token;
			sendEmail(email, resetPasswordLink);

		} catch (UsernameNotFoundException ex) {
			System.err.println(ex.getMessage());
			return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("e-mail sent!", HttpStatus.OK);

	}

	@GetMapping("/reset_password")
	public ResponseEntity<?> showResetPasswordForm(@Param(value = "token") String token) {
		UserDao user = userService.getByResetPasswordToken(token);

		if (user == null) {
			return new ResponseEntity<>("This token does not exists!", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	public void sendEmail(String recipientEmail, String link) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			helper.setFrom("dimopthan@gmail.com", "Ouzerakias Support");
			helper.setTo(recipientEmail);

			String subject = "Here's the link to reset your password";

			String content = "<p>Hello,</p>"
					+ "<p>You have requested to reset your password.</p>"
					+ "<p>Click the link below to change your password:</p>"
					+ "<p><a href=\"" + link + "\">Change my password</a></p>"
					+ "<br>"
					+ "<p>Ignore this email if you do remember your password, "
					+ "or you have not made the request.</p>";

			helper.setSubject(subject);

			helper.setText(content, true);

			mailSender.send(message);
		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	@PostMapping("/reset_password")
	public ResponseEntity<String> processResetPassword(@RequestBody Map<String, String> data) {
		String token = data.get("token");
		String password = data.get("password");

		UserDao user = userService.getByResetPasswordToken(token);

		if (user == null) {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			userService.updatePassword(user, password);

		}

		return new ResponseEntity<>("Your password has changed!", HttpStatus.OK);
	}

}
