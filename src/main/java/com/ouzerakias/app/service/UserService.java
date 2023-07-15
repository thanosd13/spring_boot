/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ouzerakias.app.service;
import com.ouzerakias.app.entity.UserDao;
import com.ouzerakias.app.repository.UserRepository;
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

    public UserDao addUser(UserDao user) {
        return userRepository.save(user);
    }
	
	
}