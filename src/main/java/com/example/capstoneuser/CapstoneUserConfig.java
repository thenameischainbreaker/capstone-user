package com.example.capstoneuser;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;


@Configuration
public class CapstoneUserConfig {
	private static final String CLIENT_ID = "881208941092-vk15t1dg16cmglp0o7hbns7i9l75ou2c.apps.googleusercontent.com";

    @Bean
    public GoogleIdTokenVerifier googleIdTokenVerifier() {
        NetHttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
    
        // Specify the CLIENT_ID of the app that accesses the backend:
	   
	    // Or, if multiple clients access the backend:
	    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
	  
        return new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
        		.setAudience(Collections.singletonList(CLIENT_ID))
        		.build();
    }
}
