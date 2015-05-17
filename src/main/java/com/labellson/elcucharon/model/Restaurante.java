/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labellson.elcucharon.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author labellson
 */
@Entity
@Table(name = "restaurante", catalog = "elcucharon", schema = "")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Restaurante.findAll", query = "SELECT r FROM Restaurante r"),
	@NamedQuery(name = "Restaurante.findById", query = "SELECT r FROM Restaurante r WHERE r.id = :id"),
	@NamedQuery(name = "Restaurante.findByLat", query = "SELECT r FROM Restaurante r WHERE r.lat = :lat"),
	@NamedQuery(name = "Restaurante.findByLng", query = "SELECT r FROM Restaurante r WHERE r.lng = :lng"),
	@NamedQuery(name = "Restaurante.findByMesas", query = "SELECT r FROM Restaurante r WHERE r.mesas = :mesas"),
	@NamedQuery(name = "Restaurante.findByFoto", query = "SELECT r FROM Restaurante r WHERE r.foto = :foto"),
	@NamedQuery(name = "Restaurante.findByNombre", query = "SELECT r FROM Restaurante r WHERE r.nombre = :nombre")})
public class Restaurante implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
	private Integer id;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "lat", precision = 10, scale = 8)
	private BigDecimal lat;
	@Column(name = "lng", precision = 11, scale = 8)
	private BigDecimal lng;
	@Column(name = "mesas")
	private Integer mesas;
	@Size(max = 255)
    @Column(name = "foto", length = 255)
	private String foto;
	@Lob
    @Size(max = 65535)
    @Column(name = "descripcion", length = 65535)
	private String descripcion;
	@Size(max = 255)
    @Column(name = "nombre", length = 255)
	private String nombre;

	public Restaurante() {
	}

	public Restaurante(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public Integer getMesas() {
		return mesas;
	}

	public void setMesas(Integer mesas) {
		this.mesas = mesas;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		if (!(object instanceof Restaurante)) {
			return false;
		}
		Restaurante other = (Restaurante) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.labellson.elcucharon.model.Restaurante[ id=" + id + " ]";
	}
	
}
