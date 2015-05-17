/*
 * Serializador para la clase User
 */
package com.labellson.elcucharon.serializers;

import com.labellson.elcucharon.model.User;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

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
