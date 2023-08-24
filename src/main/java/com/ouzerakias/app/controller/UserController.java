/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ouzerakias.app.controller;

import com.ouzerakias.app.entity.UserDao;
import com.ouzerakias.app.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author adimopo
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/update_user", method = RequestMethod.POST)
	public ResponseEntity<?> update(@RequestBody UserDao user) {

		try {
			user = userService.updateUser(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/findAll", method = GET)
	public List<UserDao> findAll() {
		return userService.findAllService();
	}

	@RequestMapping(value = "/findByUsername", method = RequestMethod.GET)
	public List<UserDao> findByUsername(@RequestParam String username) {
		return userService.findByUsername(username);
	}

	@RequestMapping(value = "/findByToken", method = RequestMethod.GET)
	public ResponseEntity<?> findByToken(@Param(value = "token") String token) {
		try {
			UserDao user = userService.getByValidationPassword(token);
			if (user == null) {	
				throw new Exception("User not found for provided token.");
			}
			userService.confirmUser(user);
			return new ResponseEntity<>("Confrimation ok!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
