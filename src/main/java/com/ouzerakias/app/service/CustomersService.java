/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ouzerakias.app.service;

import com.ouzerakias.app.common.UtilityClass;
import com.ouzerakias.app.entity.CustomersEntity;
import com.ouzerakias.app.repository.CustomersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author adimopo
 */

@Service
public class CustomersService {
	
	@Autowired
	private CustomersRepository customersRepo;
	
	@Autowired
	private UtilityClass utilityClass;
	
	public ResponseEntity<?> saveCustomer (CustomersEntity customer){
		return customersRepo.addCustomer(customer);
	}
	
	public List<CustomersEntity> fetchCustomers() {
		try {
			List<CustomersEntity> customers = customersRepo.fetchCustomers();
			for (CustomersEntity customer : customers) {
				byte[] image = customer.getImage();
				String convertedImage = utilityClass.convertToBase64(image);
				customer.setImageBase64(convertedImage);
			}
			return customers;
		} catch (Exception e) {
			System.err.println("something bad happened!");
			return null;
		}
	}
	
}
