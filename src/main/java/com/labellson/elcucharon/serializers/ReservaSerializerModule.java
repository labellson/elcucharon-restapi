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
import com.labellson.elcucharon.model.Reserva;
import com.labellson.elcucharon.model.Restaurante;
import java.io.IOException;
import org.jboss.resteasy.util.Base64;

/**
 *
 * @author labellson
 */
public class ReservaSerializerModule extends SimpleModule {

	public ReservaSerializerModule() {
		super("ReservaSerializerModule", new Version(0, 1, 0, "alpha"));
		this.addSerializer(Reserva.class, new ReservaSerializer());
	}

	private static class ReservaSerializer extends JsonSerializer<Reserva> {

		private static final String imagePath = "/home/labellson/NetBeansProjects/elcucharon-restapi/src/main/resources/images/restaurante/";
		
		@Override
		public void serialize(Reserva t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
			Restaurante r = t.getIdRestaurante();
			jg.writeStartObject();
			jg.writeNumberField("id", t.getId());
			jg.writeNumberField("fecha", t.getFecha().getTime());
			jg.writeNumberField("idUser", t.getIdUser().getId());
			jg.writeObjectFieldStart("idRestaurante");
				jg.writeNumberField("id", r.getId());
				jg.writeStringField("nombre", r.getNombre());
				jg.writeNumberField("lat", r.getLat());
				jg.writeNumberField("lng", r.getLng());
				jg.writeNumberField("mesas", r.getMesas());
				jg.writeStringField("descripcion", r.getDescripcion());
				if (r.getFoto() != null) {
					jg.writeStringField("foto", Base64.encodeFromFile(imagePath + "" + r.getFoto()));
				} else {
					jg.writeStringField("foto", null);
				}
			jg.writeEndObject();
			jg.writeEndObject();
		}

	}

}
