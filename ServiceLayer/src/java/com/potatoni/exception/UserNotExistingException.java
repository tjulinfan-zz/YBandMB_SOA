/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.exception;

/**
 *
 * @author LinFan
 */
public class UserNotExistingException extends Exception {

    /**
     * Creates a new instance of <code>UserNotExistingException</code> without
     * detail message.
     */
    public UserNotExistingException() {
    }

    /**
     * Constructs an instance of <code>UserNotExistingException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserNotExistingException(String msg) {
        super(msg);
    }
}
