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
public class PermissionDeniedException extends Exception {

    /**
     * Creates a new instance of <code>PermissionDeniedException</code> without
     * detail message.
     */
    public PermissionDeniedException() {
    }

    /**
     * Constructs an instance of <code>PermissionDeniedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PermissionDeniedException(String msg) {
        super(msg);
    }
}
