/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labellson.elcucharon.rest.api;

import com.labellson.elcucharon.serializers.JacksonObjectMapperProvider;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 *
 * @author labellson
 */
@javax.ws.rs.ApplicationPath("restapi")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}

	/**
	 * Do not modify addRestResourceClasses() method.
	 * It is automatically populated with
	 * all resources defined in the project.
	 * If required, comment out calling this method in getClasses().
	 */
	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(com.labellson.elcucharon.rest.api.ReservaFacadeREST.class);
		resources.add(com.labellson.elcucharon.rest.api.RestauranteFacadeREST.class);
		resources.add(com.labellson.elcucharon.rest.api.UserFacadeREST.class);
	}
	
}
