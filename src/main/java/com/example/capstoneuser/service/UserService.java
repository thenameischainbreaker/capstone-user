package com.example.capstoneuser.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import com.example.capstoneuser.dto.UserMetaData;
import com.example.capstoneuser.dto.UserRole;
import com.example.capstoneuser.entity.User;
import com.example.capstoneuser.exception.userAlreadyCreatedException;



public interface UserService {

	public Map<String, Object> printGoogleToken(String googleBearerToken) throws GeneralSecurityException, IOException;
	
	public boolean verifyGoogleBearerToken(String googleBearerToken) throws GeneralSecurityException, IOException;
	
	UserMetaData createUser(String googleBearerToken) throws GeneralSecurityException, IOException, userAlreadyCreatedException;
	
	double getBalanceById(int id);
	
	UserRole getUserRole(String googleBearerToken) throws GeneralSecurityException, IOException;

	
}
