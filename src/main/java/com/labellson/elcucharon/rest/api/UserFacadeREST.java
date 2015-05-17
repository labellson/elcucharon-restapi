/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labellson.elcucharon.rest.api;

import com.labellson.elcucharon.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author labellson
 */
@Stateless
@Path("user")
public class UserFacadeREST extends AbstractFacade<User> {
	//@PersistenceContext(unitName = "com.labellson_elcucharon-restapi_war_1.0PU")
	private EntityManager em;
	//private static final String JSON_RESPONSE = "{\"email\":\"%s\", \""

	public UserFacadeREST() {
		super(User.class);
	}

	@POST
    @Override
    @Consumes({"application/json"})
	@Produces(MediaType.APPLICATION_JSON)
	public User create(User entity) {
		return super.create(entity);
	}

	/*@PUT
    @Path("{id}")
    @Consumes({"application/json"})
	public void edit(@PathParam("id") Integer id, User entity) {
		super.edit(entity);
	}

	@DELETE
    @Path("{id}")
	public void remove(@PathParam("id") Integer id) {
		super.remove(super.find(id));
	}*/

	@GET
    @Path("{id}")
    @Produces({"application/json"})
	public User find(@PathParam("id") Integer id) {
		return super.find(id);
	}

	@GET
    @Override
    @Produces({"application/json"})
	public List<User> findAll() {
		return super.findAll();
	}

	@GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
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
		if(em == null){
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.labellson_elcucharon-restapi_war_1.0PU");
			em = factory.createEntityManager();
		}
		return em;
	}
	
}
