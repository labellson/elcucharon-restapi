/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labellson.elcucharon.rest.api;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.labellson.elcucharon.model.Restaurante;
import com.labellson.elcucharon.model.User;
import com.labellson.elcucharon.serializers.RestauranteSerializerPhotoModule.RestauranteSerializerPhoto;
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

/**
 *
 * @author labellson
 */
@Stateless
@Path("restaurante")
public class RestauranteFacadeREST extends AbstractFacade<Restaurante> {
	//@PersistenceContext(unitName = "com.labellson_elcucharon-restapi_war_1.0PU")
	private EntityManager em;

	public RestauranteFacadeREST() {
		super(Restaurante.class);
	}

	/*@POST
    @Override
    @Consumes({"application/json"})
	public void create(Restaurante entity) {
		super.create(entity);
	}

	@PUT
    @Path("{id}")
    @Consumes({"application/json"})
	public void edit(@PathParam("id") Integer id, Restaurante entity) {
		super.edit(entity);
	}

	@DELETE
    @Path("{id}")
	public void remove(@PathParam("id") Integer id) {
		super.remove(super.find(id));
	}*/

	@GET
    @Path("{id}")
    @Produces({"application/json;charset=UTF-8"})
	public Restaurante find(@PathParam("id") Integer id) {
		return super.find(id);
	}

	@GET
    @Override
    @Produces({"application/json;charset=UTF-8"})
	public List<Restaurante> findAll() {
		return super.findAll();
	}
	
	/*@GET
	@Produces({"application/json;charset=UTF-8"})
	@Path("image/{id}")
	public Restaurante findPhoto(@PathParam("id") Integer id){
		return find(id);
	}*/

	/*@GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
	public List<Restaurante> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
		return super.findRange(new int[]{from, to});
	}

	@GET
    @Path("count")
    @Produces("text/plain")
	public String countREST() {
		return String.valueOf(super.count());
	}*/

	@Override
	protected EntityManager getEntityManager() {
		if(em == null){
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.labellson_elcucharon-restapi_war_1.0PU");
			em = factory.createEntityManager();
		}
		return em;
	}
	
}
