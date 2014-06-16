/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author LinFan
 */
public class ResourceCreateException extends WebApplicationException {

   /**
     * Creates a new instance of <code>ResourceCreateException</code> without
     * detail message.
     */
    public ResourceCreateException() {
    }

    public ResourceCreateException(Response.Status status, String msg) {
        super(Response.status(status).entity(msg).build());
    }
    
    /**
     * Constructs an instance of <code>ResourceCreateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ResourceCreateException(String msg) {
        this(Response.Status.INTERNAL_SERVER_ERROR, msg);
    }
}
