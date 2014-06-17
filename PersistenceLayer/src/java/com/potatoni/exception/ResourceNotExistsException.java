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
public class ResourceNotExistsException extends WebApplicationException {

    /**
     * Creates a new instance of <code>ResourceNotExistsException</code> without
     * detail message.
     */
    public ResourceNotExistsException() {
        this("");
    }

    public ResourceNotExistsException(Response.Status status, String msg) {
        super(Response.status(status).entity(msg).build());
    }
    /**
     * Constructs an instance of <code>ResourceNotExistsException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ResourceNotExistsException(String msg) {
        this(Response.Status.NOT_FOUND, msg);
    }
}
