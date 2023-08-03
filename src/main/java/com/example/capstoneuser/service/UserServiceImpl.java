package com.example.capstoneuser.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.capstoneuser.dto.UserMetaData;
import com.example.capstoneuser.dto.UserRole;
import com.example.capstoneuser.entity.Admin;
import com.example.capstoneuser.entity.User;
import com.example.capstoneuser.exception.userAlreadyCreatedException;
import com.example.capstoneuser.repository.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private GoogleIdTokenVerifier googleIdTokenVerifier;
	@Autowired
	UserRepository userRepo;

	@Override
	public Map<String, Object> printGoogleToken(String googleBearerToken) throws GeneralSecurityException, IOException {
		// TODO Auto-generated method stub
		String idTokenString = googleBearerToken;
	//	System.out.println(idTokenString);
	
			
			GoogleIdToken idToken = googleIdTokenVerifier.verify(idTokenString);
			if (idToken != null) {
				  Payload payload = idToken.getPayload();

				  // Print user identifier
				  String userId = payload.getSubject();
				  System.out.println("User ID: " + userId);

				  // Get profile information from payload
				  String email = payload.getEmail();
				  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
				  String name = (String) payload.get("name");
				  String pictureUrl = (String) payload.get("picture");
				  String locale = (String) payload.get("locale");
				  String familyName = (String) payload.get("family_name");
				  String givenName = (String) payload.get("given_name");

				  // Use or store profile information
				  // ...
				  Map<String, Object> userInfo = new HashMap<>();
				  userInfo.put("email", email);
			        userInfo.put("emailVerified", emailVerified);
			        userInfo.put("name", name);
			        userInfo.put("pictureUrl", pictureUrl);
			        userInfo.put("locale", locale);
			        userInfo.put("familyName", familyName);
			        userInfo.put("givenName", givenName);
			        
			        return userInfo;
			}
			
			else {
				return null;
			}
	}

	@Override
	public boolean verifyGoogleBearerToken(String googleBearerToken) throws GeneralSecurityException, IOException {
		// TODO Auto-generated method stub
		String idTokenString = googleBearerToken;
		//	System.out.println(idTokenString);
		
				
				GoogleIdToken idToken = googleIdTokenVerifier.verify(idTokenString);
				if (idToken != null) {
					  Payload payload = idToken.getPayload();
					  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
					  return emailVerified ? true : false;
	}
				else {
					return false;
				}

}

	@Override
	public UserMetaData createUser(String googleBearerToken) throws GeneralSecurityException, IOException, userAlreadyCreatedException {
		// TODO Auto-generated method stub
		String idTokenString = googleBearerToken;
		GoogleIdToken idToken = googleIdTokenVerifier.verify(idTokenString);
		UserMetaData newUserMetaData = new UserMetaData();
		if (idToken != null) {
			  Payload payload = idToken.getPayload();

			  // Print user identifier
			  String userId = payload.getSubject();
			  // System.out.println("User ID: " + userId);

			  // Get profile information from payload
			  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			  newUserMetaData.setEmailVerified(emailVerified);
			  
			  String email = payload.getEmail();
			  newUserMetaData.setEmail(email);
			  String name = (String) payload.get("name");
			  newUserMetaData.setName(name);
			  String pictureUrl = (String) payload.get("picture");
			  newUserMetaData.setProfile_picture(pictureUrl);
			  
			  
			 //if user email already in system, then throw userAlradyCreatedException
			if ( userRepo.findByEmail(email) != null) {
			   newUserMetaData.setMessage("You are now signed in. Welcome " + newUserMetaData.getName() + "!");
			   User signedInUser = userRepo.findByEmail(email);
			   newUserMetaData.setBalance(signedInUser.getBalance());
			   newUserMetaData.setUserId(signedInUser.getU_id());
			   newUserMetaData.setRole(signedInUser.getAdmin());
			   return newUserMetaData;
			}
			else
			{   
				User newUser = new User(0, name, Admin.FALSE, email, null, null, null, 0, pictureUrl);
				User createdUser = userRepo.save(newUser);
				newUserMetaData.setBalance(createdUser.getBalance());
				newUserMetaData.setUserId(createdUser.getU_id());
				newUserMetaData.setMessage("Account Created succesfully. Welcome " + newUserMetaData.getName() + "!");
				newUserMetaData.setRole(createdUser.getAdmin());
				return newUserMetaData;
			}
		}
		else {
			newUserMetaData.setMessage("Account not verified.");
			return newUserMetaData;
		}
			  
			  // Create new User Entity POJO
			  //com.example.capstoneuser.entity.User.User(int u_id, String u_name, Admin admin, String email, String phone, 
			  //String password, String salt, double balance)
			 
		
	
	
}

	@Override
	public double getBalanceById(int id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).get().getBalance();
	}

	@Override
	public UserRole getUserRole(String googleBearerToken) throws GeneralSecurityException, IOException {
		String idTokenString = googleBearerToken;
		GoogleIdToken idToken = googleIdTokenVerifier.verify(idTokenString);
		UserRole newUserRole = new UserRole();
		if (idToken != null) {
			  Payload payload = idToken.getPayload();

			  // Print user identifier
//			  String userId = payload.getSubject();
			  // System.out.println("User ID: " + userId);
			  
			//create new UserRole
			

			  // Get profile information from payload
			 if (Boolean.valueOf(payload.getEmailVerified()))
					 newUserRole.setEmail (payload.getEmail());
			  
			User user = userRepo.findByEmail(payload.getEmail());
			newUserRole.setUserId(user.getU_id());
			newUserRole.setRole(user.getAdmin());
			newUserRole.setTokenValid(true); 
			return newUserRole;
			 }
		else {
			newUserRole.setTokenValid(false);
			return newUserRole;
		}
		
}
}
