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
import com.labellson.elcucharon.model.Reserva;
import com.labellson.elcucharon.model.User;
import com.labellson.elcucharon.model.Restaurante;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author labellson
 */
public class ReservaDeserializerModule extends SimpleModule {
	
	public ReservaDeserializerModule() {
		super("ReservaDeserializerModule", new Version(0, 1, 0, "alpha"));
		this.addDeserializer(Reserva.class, new ReservaDeserializer());
	}

	private static class ReservaDeserializer extends JsonDeserializer<Reserva> { 

		@Override
		public Reserva deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);
			Reserva r = new Reserva();
			User u = new User();
			Restaurante rs = new Restaurante();
			
			if(node.has("id")) r.setId(node.get("id").asInt());
			r.setFecha(new Date(node.get("fecha").asLong()));
			u.setId(node.get("idUser").asInt());
			r.setIdUser(u);
			rs.setId(node.get("idRestaurante").asInt());
			r.setIdRestaurante(rs);
			
			return r;
		}
	}
}
