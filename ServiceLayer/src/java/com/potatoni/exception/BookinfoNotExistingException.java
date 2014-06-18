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
public class BookinfoNotExistingException extends Exception {

    /**
     * Creates a new instance of <code>BookinfoNotExistingException</code>
     * without detail message.
     */
    public BookinfoNotExistingException() {
    }

    /**
     * Constructs an instance of <code>BookinfoNotExistingException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public BookinfoNotExistingException(String msg) {
        super(msg);
    }
}
