/*
 * Aqui registraremos los modulos
 */
package com.labellson.elcucharon.serializers;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labellson.elcucharon.deserializer.ReservaDeserializerModule;
import com.labellson.elcucharon.deserializer.UserDeserializerModule;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author labellson
 */
@Provider
public class JacksonObjectMapperProvider implements ContextResolver<ObjectMapper> {

	final ObjectMapper defaultObjectMapper;
	
	public JacksonObjectMapperProvider(){
		defaultObjectMapper = createDefaultObjectMapper();
	}
	
	@Override
	public ObjectMapper getContext(Class<?> type) {
		return defaultObjectMapper;
	}

	private static ObjectMapper createDefaultObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new UserSerializerModule());
		//mapper.registerModule(new RestauranteSerializerPhotoModule());
		mapper.registerModule(new RestauranteSerializerModule());
		mapper.registerModule(new ReservaSerializerModule());
		mapper.registerModule(new UserDeserializerModule());
		mapper.registerModule(new ReservaDeserializerModule());
		return mapper;
	}
	
}
