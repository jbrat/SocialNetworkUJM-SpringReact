package com.socialNetwork.exception;

/**
 * Class to Password Exception, this exception is throw when the twho password in form register didn't match
 * 
 * @author UJM's students
 */
public class PasswordNotMatchException extends Exception {
    
    public PasswordNotMatchException(String message) {
        super(message);
    }
}
