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
public class UserNotLoggedInException extends Exception {

    /**
     * Creates a new instance of <code>UserNotLoggedInException</code> without
     * detail message.
     */
    public UserNotLoggedInException() {
    }

    /**
     * Constructs an instance of <code>UserNotLoggedInException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserNotLoggedInException(String msg) {
        super(msg);
    }
}
