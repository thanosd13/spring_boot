/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ouzerakias.app.repository;

import com.ouzerakias.app.entity.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author adimopo
 */

@Repository
public interface UserJpaRepo extends JpaRepository<UserDao, Long>{
	
	
	UserDao findByEmail(String email);
	
	UserDao findByResetPass(String token);
	
	UserDao findByValidationPassword(String token);
	
}
