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
public class BookNotExistingException extends Exception {

    /**
     * Creates a new instance of <code>BookNotExistingException</code> without
     * detail message.
     */
    public BookNotExistingException() {
    }

    /**
     * Constructs an instance of <code>BookNotExistingException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BookNotExistingException(String msg) {
        super(msg);
    }
}
