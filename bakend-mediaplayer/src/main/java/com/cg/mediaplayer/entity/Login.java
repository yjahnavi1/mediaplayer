package com.cg.mediaplayer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="login")
public class Login {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int loginId;
	//@Length(min = 11, max = 35)
	//@Column(length = 3)
	String userName;
	//@Length(min = 4, max = 5)
	String role;
	//@Length(min = 5, max = 20)
	String password;
	
	public Login() {
		super();
	}

	public Login(int loginId, String userName, String role, String password) {
		super();
		this.loginId = loginId;
		this.userName = userName;
		this.role = role;
		this.password = password;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [loginId=" + loginId + ", userName=" + userName + ", role=" + role + ", password=" + password
				+ "]";
	}
	
	

}
