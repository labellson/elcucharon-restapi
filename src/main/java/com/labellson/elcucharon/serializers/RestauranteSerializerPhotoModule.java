/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labellson.elcucharon.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.labellson.elcucharon.model.Restaurante;
import java.io.IOException;
import org.jboss.resteasy.util.Base64;

/**
 *
 * @author labellson
 */
public class RestauranteSerializerPhotoModule extends SimpleModule {
	
	public RestauranteSerializerPhotoModule() {
		super("RestauranteSerializerPhotoModule", new Version(0, 1, 0, "alpha"));
		this.addSerializer(Restaurante.class, new RestauranteSerializerPhotoModule.RestauranteSerializerPhoto());
	}
	
	public class RestauranteSerializerPhoto extends JsonSerializer<Restaurante> {

		private static final String imagePath = "/home/labellson/NetBeansProjects/elcucharon-restapi/src/main/resources/images/restaurante/";

		@Override
		public void serialize(Restaurante t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
			jg.writeStartObject();
			if (t.getFoto() != null) {
				jg.writeStringField("foto", Base64.encodeFromFile(imagePath + "" + t.getFoto()));
			} else {
				jg.writeStringField("foto", null);
			}
			jg.writeEndObject();
		}
	}
	
}
