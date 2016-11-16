/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialNetwork.exception;

/**
 *
 * @author julien
 */
public class PasswordNotMatchException extends Exception {
    
    public PasswordNotMatchException(String message) {
        super(message);
    }
}
