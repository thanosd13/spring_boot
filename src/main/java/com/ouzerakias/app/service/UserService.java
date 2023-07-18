/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ouzerakias.app.service;
import com.ouzerakias.app.entity.UserDao;
import com.ouzerakias.app.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author adimopo
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
	
	
	public List<UserDao> findAllService() {
		try {
			return userRepository.findAllUsers();
		} catch (Exception e) {
			System.err.println("something bad happened!");
			return null;
		}
	}
	
	public List<UserDao> findByUsername(String username) {
		try {
			return userRepository.findUser(username);
		} catch (Exception e) {
			System.err.println("something bad happened!");
			return null;
		}
	}

	
}