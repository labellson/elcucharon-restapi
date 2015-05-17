/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labellson.elcucharon.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author labellson
 */
@Entity
@Table(name = "reserva", catalog = "elcucharon", schema = "")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
	@NamedQuery(name = "Reserva.findById", query = "SELECT r FROM Reserva r WHERE r.id = :id"),
	@NamedQuery(name = "Reserva.findByFecha", query = "SELECT r FROM Reserva r WHERE r.fecha = :fecha")})
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
	private Integer id;
	@Basic(optional = false)
    @NotNull
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
	private User idUser;
	@JoinColumn(name = "id_restaurante", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
	private Restaurante idRestaurante;

	public Reserva() {
	}

	public Reserva(Integer id) {
		this.id = id;
	}

	public Reserva(Integer id, Date fecha) {
		this.id = id;
		this.fecha = fecha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

	public Restaurante getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Restaurante idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Reserva)) {
			return false;
		}
		Reserva other = (Reserva) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.labellson.elcucharon.model.Reserva[ id=" + id + " ]";
	}
	
}
