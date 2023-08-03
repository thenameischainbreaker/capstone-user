package com.example.capstoneuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstoneuser.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	User findByEmail(String email);
	
	
	
}