/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.rest;

import com.potatoni.entity.User;
import com.potatoni.exception.ResourceCreateException;
import com.potatoni.exception.ResourceNotExistsException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author LinFan
 */
@Stateless
@Path("com.potatoni.entity.user")
public class UserFacadeREST extends AbstractFacade<User> {
    @PersistenceContext(unitName = "PersistenceLayerPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    /**
     *
     * @param entity
     * @throws ResourceCreateException
     */
    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(User entity) {
        if (findByUsername(entity.getUsername()) == null && findByEmail(entity.getEmail()) == null) {
            super.create(entity);
        } else {
            throw new ResourceCreateException(Response.Status.CONFLICT, "Username or email exsits");
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, User entity) {
        if (!entity.getId().equals(id) || find(id) == null)
            throw new ResourceCreateException(Response.Status.BAD_REQUEST, "Id can not be changed");
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        User user = super.find(id);
        if (user == null)
            throw new ResourceNotExistsException(Response.Status.BAD_REQUEST, "User not exists");
        super.remove(user);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public User find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<User> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    private User findByUsername(String username) {
        if (username == null) return null;
        Query q = em.createQuery("SELECT u FROM User u WHERE u.username = :username");
        q.setParameter("username", username);
        try {
            return (User)q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    private User findByEmail(String email) {
        if (email == null) return null;
        Query q = em.createQuery("SELECT u FROM User u WHERE u.email = :email");
        q.setParameter("email", email);
        try {
            return (User)q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
