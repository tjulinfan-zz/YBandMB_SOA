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
public class InternalException extends Exception {

    /**
     * Creates a new instance of <code>InternalException</code> without detail
     * message.
     */
    public InternalException() {
    }

    /**
     * Constructs an instance of <code>InternalException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InternalException(String msg) {
        super(msg);
    }
}
