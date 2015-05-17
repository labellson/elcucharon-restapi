/*
 * Serializador para la clase User
 */
package com.labellson.elcucharon.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.labellson.elcucharon.model.User;
import java.io.IOException;

/**
 *
 * @author labellson
 */
public class UserSerializerModule extends SimpleModule {
	
	public UserSerializerModule(){
		super("UserSerializerModule", new Version(0, 1, 0, "alpha"));
		this.addSerializer(User.class, new UserSerializer());
	}

	private static class UserSerializer extends JsonSerializer<User> {

		@Override
		public void serialize(User t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
			jg.writeStartObject();
			jg.writeStringField("nombre", t.getNombre());
			jg.writeEndObject();
		}
	}
}
