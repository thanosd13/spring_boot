/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ouzerakias.app.controller;

import com.ouzerakias.app.entity.UserDao;
import com.ouzerakias.app.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
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
	
	
	@RequestMapping(value ="/hello", method = GET)
    public String hello() {
        return "Hello, World!";
    }
	
	
	@RequestMapping(value ="/findAll", method = GET)
    public List<UserDao> findAll() {
        return userService.findAllService();
    }
	
	
	
}
