/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ouzerakias.app.entity;

import java.math.BigInteger;
import javax.persistence.*;

/**
 *
 * @author adimopo
 */
@Entity
@Table(name = "users")
public class UserDao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sID")
	private Long id;

	@Column(name = "Username")
	private String username;

	@Column(name = "Email")
	private String email;

	@Column(name = "Password")
	private String password;

	@Column(name = "Register_date")
	private String registerDate;

	@Column(name = "Fname")
	private String fName;

	@Column(name = "Lname")
	private String lName;

	@Column(name = "Mobile")
	private BigInteger mobile;
	
	@Column(name = "reset_pass")
    private String resetPass;
	
	@Column(name = "validation_password")
    private String validationPassword;
				
	@Column(name = "validated")
    private Integer validated;
	
	@Column(name = "admin")
    private Integer admin;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}


	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public BigInteger getMobile() {
		return mobile;
	}

	public void setMobile(BigInteger mobile) {
		this.mobile = mobile;
	}

	public String getResetPass() {
		return resetPass;
	}

	public void setResetPass(String resetPass) {
		this.resetPass = resetPass;
	}

	public String getValidationPassword() {
		return validationPassword;
	}

	public void setValidationPassword(String validationPassword) {
		this.validationPassword = validationPassword;
	}

	public Integer getValidated() {
		return validated;
	}

	public void setValidated(Integer validated) {
		this.validated = validated;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}
	
}
