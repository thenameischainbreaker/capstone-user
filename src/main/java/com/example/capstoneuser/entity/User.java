package com.example.capstoneuser.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int u_id;
	private String u_name;
	@Enumerated(EnumType.STRING)
	private Admin admin;
	private String email;
	private String phone;
	private String password;
	private String salt;
	private double balance;
	private String profile_picture;
	
	


	public User(int u_id, String u_name, Admin admin, String email, String phone, String password, String salt,
			double balance, String profile_picture) {
		super();
		this.u_id = u_id;
		this.u_name = u_name;
		this.admin = admin;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.salt = salt;
		this.balance = balance;
		this.profile_picture = profile_picture;
	}
	
	public User() {
		
	}


	public int getU_id() {
		return u_id;
	}


	public void setU_id(int u_id) {
		this.u_id = u_id;
	}


	public String getU_name() {
		return u_name;
	}


	public void setU_name(String u_name) {
		this.u_name = u_name;
	}


	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getSalt() {
		return salt;
	}


	public void setSalt(String salt) {
		this.salt = salt;
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


	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", u_name=" + u_name + ", admin=" + admin + ", email=" + email + "]";
	}
	
	
	
	
	
	
	
}
