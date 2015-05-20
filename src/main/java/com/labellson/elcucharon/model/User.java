/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labellson.elcucharon.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author labellson
 */
@Entity
@Table(name = "user", catalog = "elcucharon", schema = "", uniqueConstraints = {
	@UniqueConstraint(columnNames = {"email"}),
	@UniqueConstraint(columnNames = {"movil"}),
	@UniqueConstraint(columnNames = {"dni"})})
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
	@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
	@NamedQuery(name = "User.findByPass", query = "SELECT u FROM User u WHERE u.pass = :pass"),
	@NamedQuery(name = "User.findByMovil", query = "SELECT u FROM User u WHERE u.movil = :movil"),
	@NamedQuery(name = "User.findByDni", query = "SELECT u FROM User u WHERE u.dni = :dni"),
	@NamedQuery(name = "User.findByNombre", query = "SELECT u FROM User u WHERE u.nombre = :nombre"),
	@NamedQuery(name = "User.findByFoto", query = "SELECT u FROM User u WHERE u.foto = :foto")})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
	private Integer id;
	// @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
	@Size(max = 255)
    @Column(name = "email", length = 255)
	private String email;
	@Size(max = 255)
    @Column(name = "pass", length = 255)
	private String pass;
	@Size(max = 15)
	@Column(name = "movil", length = 15)
	private String movil;
	@Size(max = 255)
    @Column(name = "dni", length = 255)
	private String dni;
	@Size(max = 255)
    @Column(name = "nombre", length = 255)
	private String nombre;
	@Size(max = 255)
    @Column(name = "foto", length = 255)
	private String foto;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
	private Collection<Reserva> reservaCollection;
	
	//Indica que ha habido un problema
	@Transient //Asi el ORM no lo mapea para buscarlo en la BD
	private String problem;

	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@XmlTransient
	public Collection<Reserva> getReservaCollection() {
		return reservaCollection;
	}

	public void setReservaCollection(Collection<Reserva> reservaCollection) {
		this.reservaCollection = reservaCollection;
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
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.labellson.elcucharon.model.User[ id=" + id + " ]";
	}
	
	public void setProblem(String prob){
		problem = prob;
	}
	
	public String getProblem(){
		return problem;
	}
	
	public void clearProblem(){
		problem = null;
	}
}
