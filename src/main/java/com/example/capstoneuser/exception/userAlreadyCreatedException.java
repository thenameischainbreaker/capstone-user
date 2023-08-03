package com.example.capstoneuser.exception;

public class userAlreadyCreatedException extends Exception {

    public userAlreadyCreatedException() {
        // Use the default constructor from the superclass
    }

    public userAlreadyCreatedException(String message) {
        // Call the constructor of the superclass with the custom message
        super(message);
    }
    
}
