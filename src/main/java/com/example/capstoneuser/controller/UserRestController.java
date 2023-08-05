package com.example.capstoneuser.controller;



import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstoneuser.dto.UserMetaData;
import com.example.capstoneuser.dto.UserRole;
import com.example.capstoneuser.entity.User;
import com.example.capstoneuser.exception.userAlreadyCreatedException;
import com.example.capstoneuser.service.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.gson.Gson;


@RequestMapping("/user")
@RestController
@CrossOrigin(origins = {"https://domainofchain.s3.us-east-2.amazonaws.com", "http://localhost:4200/","https://capstone-angular-jj.s3.us-east-2.amazonaws.com"})
public class UserRestController {

@Autowired
private GoogleIdTokenVerifier googleIdTokenVerifier;
@Autowired
UserService userService;


@PostMapping("/printGoogleToken")
	public ResponseEntity printGoogleToken(@RequestBody String token) {
	try {
		return new ResponseEntity<>(userService.printGoogleToken(token), HttpStatus.OK);
	
		
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(token + "\n" + e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	
}

@PostMapping("/createUser")
public String createUser(@RequestBody String token) {
	Gson gson = new Gson();
	try {
		return gson.toJson(userService.createUser(token));
	} catch (GeneralSecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		UserMetaData newUserMetaData = new UserMetaData();
		newUserMetaData.setMessage("e.message");
		return gson.toJson(newUserMetaData);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		UserMetaData newUserMetaData = new UserMetaData();
		newUserMetaData.setMessage("e.message");
		return gson.toJson(newUserMetaData);
	} catch (userAlreadyCreatedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		UserMetaData newUserMetaData = new UserMetaData();
		newUserMetaData.setMessage("e.message");
		return gson.toJson(newUserMetaData);
	}
}

@GetMapping("/getBalanceById")

public double getBalanceById(@RequestParam (name = "id") int id ) {
	
	try {
		return userService.getBalanceById(id);
	} catch (Exception e) {
	
		e.printStackTrace();
		return -1;
	}
}

@PostMapping("/getUserRole")

public UserRole getUserRole(@RequestBody String token) {
	try {
		return userService.getUserRole(token);
	}
	 catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		UserRole newUserRole = new UserRole();
		newUserRole.setTokenValid(false);
		return newUserRole;
	}
	
	
	
}
}


