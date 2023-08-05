package com.example.capstoneuser.dto;

import com.example.capstoneuser.entity.Admin;

public class UserMetaData {
	@Override
	public String toString() {
		return "UserMetaData [userId=" + userId + ", role=" + role + ", email=" + email + ", name=" + name
				+ ", balance=" + balance + ", profile_picture=" + profile_picture + ", message=" + message
				+ ", emailVerified=" + emailVerified + "]";
	}

	private int userId;
	private Admin role;
	private String email;
	private String name;
	private double balance;
	private String profile_picture;
	private String message;
	private boolean emailVerified;
	
	public UserMetaData() {
		
	}




	public UserMetaData(int userId, Admin role, String email, String name, double balance, String profile_picture,
			String message, boolean emailVerified) {
		super();
		this.userId = userId;
		this.role = role;
		this.email = email;
		this.name = name;
		this.balance = balance;
		this.profile_picture = profile_picture;
		this.message = message;
		this.emailVerified = emailVerified;
	}




	public boolean isEmailVerified() {
		return emailVerified;
	}




	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getMessage() {
		return message;
	}




	public void setMessage(String message) {
		this.message = message;
	}




	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Admin getRole() {
		return role;
	}

	public void setRole(Admin role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getProfile_picture() {
		return profile_picture;
	}

	public void setProfile_picture(String profile_picture) {
		this.profile_picture = profile_picture;
	}
	
	
	

	
	
	
}
