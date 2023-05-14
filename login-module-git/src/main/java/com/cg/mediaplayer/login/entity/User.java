package com.cg.mediaplayer.login.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userLoginId;
	
	@NotNull(message = "Firstname cannot be null!")
	@NotBlank(message = "Firstname connot be blank!")
	private String firstName;
	
	@NotNull(message = "Lastname cannot be null!")
	@NotBlank(message = "Lastname connot be blank!")
	private String lastName;
	
	@NotNull(message = "Username cannot be null!")
	@NotBlank(message = "Username connot be blank!")
	private String userName;
	
	private String picture;
	
	@Email
	private String emailAddress;
	
	private String password;
	
	public User(){
		
	}

	public User(int userLoginId,
			@NotNull(message = "Firstname cannot be null!") @NotBlank(message = "Firstname connot be blank!") String firstName,
			@NotNull(message = "Lastname cannot be null!") @NotBlank(message = "Lastname connot be blank!") String lastName,
			@NotNull(message = "Username cannot be null!") @NotBlank(message = "Username connot be blank!") String userName,
			String picture, @Email String emailAddress, String password) {
		super();
		this.userLoginId = userLoginId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.picture = picture;
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public int getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(int userLoginId) {
		this.userLoginId = userLoginId;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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

	public void setSubscribed(boolean b) {
		// TODO Auto-generated method stub
	}
	
}