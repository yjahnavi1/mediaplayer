package com.cg.mediaplayer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int adminLoginId;
	
	@NotNull(message = "Firstname cannot be null!")
	@NotBlank(message = "Firstname connot be blank!")
	private String firstName;
	
	@NotNull(message = "Lastname cannot be null!")
	@NotBlank(message = "Lastname connot be blank!")
	private String lastName;
	
	@NotNull(message = "Username cannot be null!")
	@NotBlank(message = "Username connot be blank!")
	private String userName;

	@Email
	private String emailAddress;
	
	private String password;
	
	private String picture;
	
	public Admin(){
		
	}

	public Admin(int adminLoginId,
			@NotNull(message = "Firstname cannot be null!") @NotBlank(message = "Firstname connot be blank!") String firstName,
			@NotNull(message = "Lastname cannot be null!") @NotBlank(message = "Lastname connot be blank!") String lastName,
			@NotNull(message = "Username cannot be null!") @NotBlank(message = "Username connot be blank!") String userName,
			@Email String emailAddress, String password, String picture) {
		super();
		this.adminLoginId = adminLoginId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.picture = picture;
	}

	public int getAdminLoginId() {
		return adminLoginId;
	}

	public void setAdminLoginId(int adminLoginId) {
		this.adminLoginId = adminLoginId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Admin [adminLoginId=" + adminLoginId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", emailAddress=" + emailAddress + ", password=" + password + ", picture="
				+ picture + "]";
	}
	

	
}