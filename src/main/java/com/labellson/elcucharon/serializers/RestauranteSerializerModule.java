/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labellson.elcucharon.serializers;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.labellson.elcucharon.model.Restaurante;
import java.io.IOException;
import javax.json.Json;
import javax.json.stream.JsonGeneratorFactory;
import org.jboss.resteasy.util.Base64;

/**
 *
 * @author labellson
 */
public class RestauranteSerializerModule extends SimpleModule{
	
	public RestauranteSerializerModule(){
		super("RestauranteSerializerModule", new Version(0, 1, 0, "alpha"));
		this.addSerializer(Restaurante.class,  new RestauranteSerializer());
	}

	private static class RestauranteSerializer extends JsonSerializer<Restaurante> {

		private static final String imagePath = "/home/labellson/NetBeansProjects/elcucharon-restapi/src/main/resources/images/restaurante/";
		
		@Override
		public void serialize(Restaurante t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
			jg.writeStartObject();
			jg.writeNumberField("id", t.getId());
			jg.writeStringField("nombre", t.getNombre());
			jg.writeNumberField("lat", t.getLat());
			jg.writeNumberField("lng", t.getLng());
			jg.writeNumberField("mesas", t.getMesas());
			jg.writeStringField("descripcion", t.getDescripcion());
			if(t.getFoto() != null){
				jg.writeStringField("foto", Base64.encodeFromFile(imagePath+""+t.getFoto()));
			}else{
				jg.writeStringField("foto", null);
			}
			jg.writeEndObject();
		}
	}
	
}
