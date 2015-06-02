/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labellson.elcucharon.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.labellson.elcucharon.model.User;
import java.io.IOException;
import org.jboss.resteasy.util.Base64;

/**
 *
 * @author labellson
 */
public class UserDeserializerModule extends SimpleModule {
	
	public UserDeserializerModule(){
		super("UserDeserializerModule", new Version(0, 1, 0, "alpha"));
		this.addDeserializer(User.class, new UserDeserializer());
	}

	private static class UserDeserializer extends JsonDeserializer<User>{

		private static final String imagePath = "/home/labellson/NetBeansProjects/elcucharon-restapi/src/main/resources/images/user/";

		@Override
		public User deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);
			User u = new User();
			
			if(node.has("id")) u.setId(node.get("id").asInt());
			u.setEmail(node.get("email").asText());
			if(node.has("pass")) u.setPass(node.get("pass").asText());
			if(node.has("movil")) u.setMovil(node.get("movil").asText());
			if(node.has("dni")) u.setDni(node.get("dni").asText());
			if(node.has("nombre")) u.setNombre(node.get("nombre").asText());
			if(node.has("foto")){
				String ruta = "user_"+u.getEmail()+".png";
				String pic_b64 = node.get("foto").asText();
				Base64.decodeToFile(pic_b64, imagePath + ruta);
				u.setFoto(ruta);
			}
			return u;
		}
	}
	
}
