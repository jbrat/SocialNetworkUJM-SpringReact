package com.socialNetwork.exception;

/**
 * Class to Email Exception, this exception is throws when the user try to register an email which is allready use by other user
 * 
 * @author UJM's students
 */
public class EmailAlreadyExistException extends Exception {
    
    public EmailAlreadyExistException(String message) {
        super(message);
    }
    
}
