/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ouzerakias.app.repository;

import com.ouzerakias.app.entity.UserDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

/**
 *
 * @author adimopo
 */
@Repository
@Transactional
public class UserRepository {


    @PersistenceContext
    private EntityManager entityManager;

    public ResponseEntity<String> addUser(UserDao user) {
		
        List<UserDao> users = findUser(user.getUsername());
		UserDao userExists = users.isEmpty() ? null : users.get(0);

        if (userExists == null) {
            String sql = "INSERT INTO Users (Username, Password, Email, Register_date, Fname, Lname, Mobile) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try {
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter(1, user.getUsername());
                query.setParameter(2, user.getPassword());
                query.setParameter(3, user.getEmail());
                query.setParameter(4, user.getRegisterDate());
                query.setParameter(5, user.getfName());
                query.setParameter(6, user.getlName());
                query.setParameter(7, user.getMobile());
                query.executeUpdate();

                return new ResponseEntity<>("User inserted", HttpStatus.OK);
            } catch (Exception e) {
                // Handle any exceptions here
                return new ResponseEntity<>("Error occurred while inserting user", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
    }

	public List<UserDao> findUser(String username) {
		String sql = "SELECT * FROM Users WHERE Username=?";
		Query query = entityManager.createNativeQuery(sql, UserDao.class);
		query.setParameter(1, username);
		try {
			return (List<UserDao>) query.getResultList();
		} catch (NoResultException e) {
			// User not found, handle appropriately
			return null;
		}
	}
	
	public List<UserDao> findAllUsers() {
		String sql = "SELECT * FROM Users";
		Query query = entityManager.createNativeQuery(sql, UserDao.class);
		try {
			return (List<UserDao>) query.getResultList();
		} catch (NoResultException e) {
			// User not found, handle appropriately
			return null;
		}
	}
}