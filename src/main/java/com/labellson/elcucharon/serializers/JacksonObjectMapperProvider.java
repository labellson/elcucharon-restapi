/*
 * Aqui registraremos los modulos
 */
package com.labellson.elcucharon.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
		return mapper;
	}
	
}
