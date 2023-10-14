package com.Watcharakorn.timeTableSheduler.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String userName;
	private String userPassword;
	private Boolean staff;
	public User( String userName, String userPassword) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.staff = false;
	}
	public User() {
		super();
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Boolean getStaff() {
		return staff;
	}
	public void setStaff(Boolean staff) {
		this.staff = staff;
	}
	
	
	
}
