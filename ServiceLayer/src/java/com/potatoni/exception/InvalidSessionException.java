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
public class InvalidSessionException extends Exception {

    /**
     * Creates a new instance of <code>InvalidSessionException</code> without
     * detail message.
     */
    public InvalidSessionException() {
    }

    /**
     * Constructs an instance of <code>InvalidSessionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidSessionException(String msg) {
        super(msg);
    }
}
