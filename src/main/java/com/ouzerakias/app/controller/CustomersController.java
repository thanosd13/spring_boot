/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ouzerakias.app.controller;

import com.ouzerakias.app.entity.CustomersEntity;
import com.ouzerakias.app.service.CustomersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author adimopo
 */

@RestController
@RequestMapping("/customer")
public class CustomersController {
	
	@Autowired
	CustomersService customerService;
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> saveCustomer(@RequestBody CustomersEntity customer){
		return customerService.saveCustomer(customer);
	}
	
	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	public List<CustomersEntity> findByUsername() {
		return customerService.fetchCustomers();
	}
}
