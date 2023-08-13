/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ouzerakias.app.service;
import com.ouzerakias.app.entity.UserDao;
import com.ouzerakias.app.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ouzerakias.app.repository.UserJpaRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author adimopo
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
	
	@Autowired
	private UserJpaRepo userJpaRepo;
	
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

	
	public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException  {
		UserDao user = userJpaRepo.findByEmail(email);
		if (user != null) {
			user.setResetPass(token);
			userJpaRepo.save(user);
		} else {
			throw new UsernameNotFoundException("Could not find any customer with the email " + email);
		}
    }
	
	public UserDao getByValidationPassword(String token) {
        return userJpaRepo.findByValidationPassword(token);
    }
	
	public UserDao getByResetPasswordToken(String token) {
        return userJpaRepo.findByResetPass(token);
    }
	
	public UserDao updateUser(UserDao user){
		userJpaRepo.save(user);
		return user;
	}
	
	public void confirmUser(UserDao user) {
		user.setValidationPassword(null);
		user.setValidated(1);
        userJpaRepo.save(user);
    }

     
    public void updatePassword(UserDao user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
         
        user.setResetPass(null);
        userJpaRepo.save(user);
    }

	
}