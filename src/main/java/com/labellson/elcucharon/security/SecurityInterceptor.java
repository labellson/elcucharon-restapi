/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labellson.elcucharon.security;

import com.labellson.elcucharon.model.User;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.security.PermitAll;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.util.Base64;

/**
 *
 * @author labellson
 */
@Provider
@Authenticated
public class SecurityInterceptor implements ContainerRequestFilter {

	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";
	private static final ServerResponse ACCESS_DENIED = new ServerResponse("Acceso denegado en este recurso", 401, new Headers<Object>());
	private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500, new Headers<Object>());

	private EntityManager em;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		//ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
		//Method method = methodInvoker.getMethod();
		//PostMatchContainerRequestContext pmContext = (PostMatchContainerRequestContext) requestContext;
		//Method method = pmContext.getResourceMethod().getMethod();

		//Si se permite a todos pues se permite
		//if (method.isAnnotationPresent(PermitAll.class)) return;

		//Si no, habra que comprobar mirando si estan los header de autenticación
		final MultivaluedMap<String, String> headers = requestContext.getHeaders();

		final List<String> autorization = headers.get(AUTHORIZATION_PROPERTY);

		if (autorization == null || autorization.isEmpty()) {
			requestContext.abortWith(ACCESS_DENIED);
			return;
		}

		final String encodedUserPass = autorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

		String userAndPass;
		try {
			userAndPass = new String(Base64.decode(encodedUserPass));
		} catch (IOException ex) {
			ex.printStackTrace();
			requestContext.abortWith(SERVER_ERROR);
			return;
		}

		//Separar el usuario y la contraseña
		final StringTokenizer tokenizer = new StringTokenizer(userAndPass, ":");
		final String username = tokenizer.nextToken();
		final String pass = tokenizer.nextToken();

		//Esto solo es para debuggear
		System.out.println(username);
		System.out.println(pass);

		//Verificar que el usuario es quien dice ser, y no se trata de un impostor que quiere hacer cosas malas
		if (!isUserAllowed(username, pass)) {
			requestContext.abortWith(ACCESS_DENIED);
			return;
		}
		//Si se ha llegado hasta aqui, es que el usuario es valido
	}
	
	private boolean isUserAllowed(String username, String pass) {
		if (em == null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.labellson_elcucharon-restapi_war_1.0PU");
			em = factory.createEntityManager();
		}
		
		Query query = em.createNamedQuery("User.findByEmail", User.class);
		query.setParameter("email", username);
		User user;
		try{
			user =  (User) query.getSingleResult();
		}catch(NoResultException e){
			return false;
		}
		return username.equals(user.getEmail()) && pass.equals(user.getPass());
	}
}
