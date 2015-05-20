/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labellson.elcucharon.rest.api;

import com.labellson.elcucharon.model.User;
import com.labellson.elcucharon.security.Authenticated;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
import javax.ws.rs.core.MediaType;
import org.eclipse.persistence.exceptions.DatabaseException;

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
		String problem = checkUser(entity, -1);
		if(problem != null){ entity.setProblem(problem); return entity; }
		//Si se llega aqui, es que todo ha ido bien y se guarda el usuario
		return super.create(entity);
	}

	@PUT
    @Path("{id}")
    @Consumes({"application/json"})
	@Produces(MediaType.APPLICATION_JSON)
	public User edit(@PathParam("id") Integer id, User entity) {
		String problem = checkUser(entity, id);
		if(problem != null){ entity.setProblem(problem); return entity; }
		return super.edit(entity);
	}

	/*@DELETE
    @Path("{id}")
	public void remove(@PathParam("id") Integer id) {
		super.remove(super.find(id));
	}*/

	@GET
    @Path("{id}")
    @Produces({"application/json"})
	@Authenticated
	public User find(@PathParam("id") Integer id) {
		return super.find(id);
	}
	
	@GET
	@Path("email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticated
	public User findByEmail(@PathParam("email") String email){
		Query q = getEntityManager().createNamedQuery("User.findByEmail", User.class);
		q.setParameter("email", email);
		return (User) q.getSingleResult();
	}
	

	/*@GET
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
	}*/

	@Override
	protected EntityManager getEntityManager() {
		if(em == null){
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.labellson_elcucharon-restapi_war_1.0PU");
			em = factory.createEntityManager();
		}
		return em;
	}
	
	private String checkUser(User entity, int id){
		//Buscaremos que no se repita el email, movil o dni
		EntityManager em = getEntityManager();

		//Primero por email
		Query q = em.createNamedQuery("User.findByEmail", User.class);
		q.setParameter("email", entity.getEmail());
		List<User> user_list = q.getResultList();
		if (!user_list.isEmpty()) {
			//entity.setProblem("email");
			if(id != user_list.get(0).getId()) return "email";
		}

		//Segundo el movil
		q = em.createNamedQuery("User.findByMovil", User.class);
		q.setParameter("movil", entity.getMovil());
		user_list = q.getResultList();
		if (!user_list.isEmpty()) {
			//entity.setProblem("movil");
			if (id != user_list.get(0).getId()) return "movil";
		}

		//Tercero el dni
		q = em.createNamedQuery("User.findByDni", User.class);
		q.setParameter("dni", entity.getDni());
		user_list = q.getResultList();
		if (!user_list.isEmpty()) {
			//entity.setProblem("dni");
			if (id != user_list.get(0).getId()) return "dni";
		}
		
		return null;
	}
}
