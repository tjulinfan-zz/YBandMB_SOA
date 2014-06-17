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
public class ServerNetworkException extends Exception {

    /**
     * Creates a new instance of <code>ServerNetworkException</code> without
     * detail message.
     */
    public ServerNetworkException() {
    }

    /**
     * Constructs an instance of <code>ServerNetworkException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ServerNetworkException(String msg) {
        super(msg);
    }
}
