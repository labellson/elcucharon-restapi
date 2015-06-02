/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labellson.elcucharon.rest.api;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labellson.elcucharon.model.Reserva;
import com.labellson.elcucharon.security.Authenticated;
import java.beans.Statement;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 *
 * @author labellson
 */
@Stateless
@Path("reserva")
public class ReservaFacadeREST extends AbstractFacade<Reserva> {
	//@PersistenceContext(unitName = "com.labellson_elcucharon-restapi_war_1.0PU")
	private EntityManager em;

	public ReservaFacadeREST() {
		super(Reserva.class);
	}

	@POST
    @Override
    @Consumes({"application/json"})
	@Produces(MediaType.APPLICATION_JSON+";chatset=UTF-8")
	@Authenticated
	public Reserva create(Reserva entity) {
		return super.create(entity);
	}
	
	@GET
	@Path("user/{id}")
	@Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
	@Authenticated
	public List<Reserva> findReservaByIdUsuario(@PathParam("id") int idUsuario){
		Query q = getEntityManager().createNamedQuery("Reserva.findByIdUser", Reserva.class);
		q.setParameter("idUser", idUsuario);
		return q.getResultList();
	}
	
	@GET
	@Path("restaurante/{id}")
	@Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
	public Response findFechasReservas(@PathParam("id") int idRestaurante){
		/*Query q = getEntityManager().createNamedQuery("Reserva.findFechasReservas", Reserva.class);
		q.setParameter("idRestaurante", idRestaurante);
		final List<Reserva> reservas = q.getResultList();*/
		
		//No se va a utilizar el orm en esta por que no tiene lo que quiero, o no tengo ganas de seguir buscando :D
		List<Date> d = new ArrayList<Date>();
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/elcucharon?zeroDateTimeBehavior=convertToNull", "root", "");
			java.sql.Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("select fecha from (select id_restaurante, date(fecha) as fecha, count(*) as count from reserva where id_restaurante = "+idRestaurante+" and fecha >= now() group by id_restaurante, date(fecha)) as full where count = 5");
			
			while(rs.next()){
				d.add(rs.getDate(1));
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(ReservaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
		}finally{
			try {
				conexion.close();
			} catch (SQLException ex) {
				Logger.getLogger(ReservaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		final List<Date> dates = d;
		
		//Creamos la respuesta en JSON
		StreamingOutput stream = new StreamingOutput() {

			@Override
			public void write(OutputStream output) throws IOException, WebApplicationException {
				JsonGenerator jg = new ObjectMapper().getFactory().createGenerator(output, JsonEncoding.UTF8);
				jg.writeStartArray();
				
				for(Date date : dates){
					jg.writeStartObject();
					jg.writeNumberField("fecha", date.getTime());
					jg.writeEndObject();
				}
				jg.writeEndArray();
				jg.flush();
				jg.close();
			}
		};
		
		return Response.ok().entity(stream).type(MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("restaurante/{id}/{fecha}")
	@Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
	public Response findHorasFecha(@PathParam("id") int idRestaurante, @PathParam("fecha") long dateLong){
			Connection conexion = null;
			String[] h_disp = {"21:00", "21:15", "21:30", "21:45", "22:00"};
			List<String> horas_reserva = new ArrayList<String>();
			SimpleDateFormat df_date = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat df_time = new SimpleDateFormat("HH:mm");
			Date dateArg = new Date(dateLong);
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/elcucharon?zeroDateTimeBehavior=convertToNull", "root", "");
			java.sql.Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("select fecha from reserva where id_restaurante = "+idRestaurante+" and date(fecha) = '"+df_date.format(dateArg)+"'");
			
			while(rs.next()){
				horas_reserva.add(df_time.format(rs.getTimestamp(1)));
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReservaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
		}finally{
				try {
					conexion.close();
				} catch (SQLException ex) {
					Logger.getLogger(ReservaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
				}
		}
		
		//Buscaremos las horas devueltas en el array de disponibles, si se encuentran, es que ya hay reserva
		List<String> h = new ArrayList<String>();
		
		for(String s : h_disp){
			if(!horas_reserva.contains(s)) h.add(s);
		}
		
		final List<String> hF = h;
		
		//Creamos la respuesta en JSON
		StreamingOutput stream = new StreamingOutput() {

			@Override
			public void write(OutputStream output) throws IOException, WebApplicationException {
				JsonGenerator jg = new ObjectMapper().getFactory().createGenerator(output, JsonEncoding.UTF8);
				jg.writeStartArray();

				for (String hora : hF) {
					jg.writeStartObject();
					jg.writeStringField("hora", hora);
					jg.writeEndObject();
				}
				jg.writeEndArray();
				jg.flush();
				jg.close();
			}
		};

		return Response.ok().entity(stream).type(MediaType.APPLICATION_JSON).build();
		
	}

	/*@PUT
    @Path("{id}")
    @Consumes({"application/json"})
	public void edit(@PathParam("id") Integer id, Reserva entity) {
		super.edit(entity);
	}*/

	@DELETE
    @Path("{id}")
	@Authenticated
	public void remove(@PathParam("id") Integer id) {
		super.remove(super.find(id));
	}

	/*@GET
    @Path("{id}")
    @Produces({"application/json"})
	public Reserva find(@PathParam("id") Integer id) {
		return super.find(id);
	}

	@GET
    @Override
    @Produces({"application/json"})
	public List<Reserva> findAll() {
		return super.findAll();
	}*/

	/*@GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
	public List<Reserva> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
		return super.findRange(new int[]{from, to});
	}

	@GET
    @Path("count")
    @Produces("text/plain")
	public String countREST() {
		return String.valueOf(super.count());
	}*/

	@Override
	protected EntityManager getEntityManager() {
		if (em == null){
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.labellson_elcucharon-restapi_war_1.0PU");
			em = factory.createEntityManager();
		}
		return em;
	}
	
}
