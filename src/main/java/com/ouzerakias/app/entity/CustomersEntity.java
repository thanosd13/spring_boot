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
@Table(name = "customers")
public class CustomersEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cID")
	private Long id;

	@Column(name = "Username")
	private String username;

	@Column(name = "Email")
	private String email;

	@Column(name = "Password")
	private String password;

	@Column(name = "Ouzeri")
	private String ouzeri;

	@Column(name = "Create_date")
	private String createDate;

	@Column(name = "Subscription")
	private String subscription;

	@Column(name = "SubEndDate")
	private BigInteger subEndDate;
	
	@Column(name = "Address")
    private String address;
	
	@Column(name = "Fname")
    private String fName;
				
	@Column(name = "Lname")
    private String lName;
	
	@Column(name = "Mobile")
    private Integer mobile;
	
	@Lob
	@Column(name = "Image")
    private byte [] image;
	
	@Column(name = "Description")
    private String description;
	
	private String imageBase64;
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getOuzeri() {
		return ouzeri;
	}

	public void setOuzeri(String ouzeri) {
		this.ouzeri = ouzeri;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	public BigInteger getSubEndDate() {
		return subEndDate;
	}

	public void setSubEndDate(BigInteger subEndDate) {
		this.subEndDate = subEndDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
	
}
