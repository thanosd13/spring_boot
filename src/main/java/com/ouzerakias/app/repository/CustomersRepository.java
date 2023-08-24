/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ouzerakias.app.repository;

import com.ouzerakias.app.entity.CustomersEntity;
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
public class CustomersRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	
	public List<CustomersEntity> fetchCustomers(){
		String sql = "SELECT * FROM customers";
		Query query = entityManager.createNativeQuery(sql, CustomersEntity.class);
		try {
			return (List<CustomersEntity>) query.getResultList();
		} catch (NoResultException e) {
			// User not found, handle appropriately
			return null;
		}
	}
	
	
	public ResponseEntity<?> addCustomer(CustomersEntity customer){
		
		List<CustomersEntity> customers = findCustomer(customer.getEmail());
		CustomersEntity customerExists = customers.isEmpty() ? null : customers.get(0);
		
			if (customerExists == null) {
            String sql = "INSERT INTO customers (Username, Password, Email, Ouzeri, Create_date, Subscription, SubEndDate, Address, Fname, Lname, Mobile, Image, Description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter(1, customer.getUsername());
                query.setParameter(2, customer.getPassword());
                query.setParameter(3, customer.getEmail());
                query.setParameter(4, customer.getOuzeri());
                query.setParameter(5, customer.getCreateDate());
                query.setParameter(6, customer.getSubscription());
                query.setParameter(7, customer.getSubEndDate());
				query.setParameter(8, customer.getAddress());
				query.setParameter(9, customer.getfName());
				query.setParameter(10, customer.getlName());
				query.setParameter(11, customer.getMobile());
				query.setParameter(12, customer.getImage());
				query.setParameter(13, customer.getDescription());
                query.executeUpdate();

                return new ResponseEntity<>("Customer inserted", HttpStatus.OK);
            } catch (Exception e) {
                // Handle any exceptions here
				
				System.err.println(e.getMessage());
                return new ResponseEntity<>("Error occurred while inserting customer", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Customer already exists", HttpStatus.CONFLICT);
        }
		
	}
	
	
	public List<CustomersEntity> findCustomer(String username) {
		String sql = "SELECT * FROM customers WHERE Email=?";
		Query query = entityManager.createNativeQuery(sql, CustomersEntity.class);
		query.setParameter(1, username);
		try {
			return (List<CustomersEntity>) query.getResultList();
		} catch (NoResultException e) {
			// User not found, handle appropriately
			return null;
		}
	}
}
