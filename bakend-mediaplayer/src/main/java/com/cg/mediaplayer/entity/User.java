package com.cg.mediaplayer.entity;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	//private String picture;
	
	@Email
	private String emailAddress;
	
	private String password;
	
	@OneToMany(targetEntity=Videos.class,cascade=CascadeType.ALL)
	@JoinColumn(name="uv_fk",referencedColumnName="userLoginId")
	private List<Videos> videos;
	
	public User(){
		super();
	}

	public User(int userLoginId,
			@NotNull(message = "Firstname cannot be null!") @NotBlank(message = "Firstname connot be blank!") String firstName,
			@NotNull(message = "Lastname cannot be null!") @NotBlank(message = "Lastname connot be blank!") String lastName,
			@NotNull(message = "Username cannot be null!") @NotBlank(message = "Username connot be blank!") String userName,
			@Email String emailAddress, String password, List<Videos> videos) {
		super();
		this.userLoginId = userLoginId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.videos = videos;
	}
	
	

	public User(int userLoginId,
			@NotNull(message = "Firstname cannot be null!") @NotBlank(message = "Firstname connot be blank!") String firstName,
			@NotNull(message = "Lastname cannot be null!") @NotBlank(message = "Lastname connot be blank!") String lastName,
			@NotNull(message = "Username cannot be null!") @NotBlank(message = "Username connot be blank!") String userName,
			@Email String emailAddress, String password) {
		super();
		this.userLoginId = userLoginId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
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

	public List<Videos> getVideos() {
		return videos;
	}

	public void setVideos(List<Videos> videos) {
		this.videos = videos;
	}

	@Override
	public String toString() {
		return "User [userLoginId=" + userLoginId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", emailAddress=" + emailAddress + ", password=" + password + ", videos="
				+ videos + "]";
	}
	
	


	
}