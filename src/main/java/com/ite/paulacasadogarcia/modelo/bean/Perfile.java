package com.ite.paulacasadogarcia.modelo.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the perfiles database table.
 * 
 */
@Entity
@Table(name="perfiles")
@NamedQuery(name="Perfile.findAll", query="SELECT p FROM Perfile p")
public class Perfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PERFIL")
	private int idPerfil;

	private String descripcion;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="perfiles")
	private List<Usuario> usuarios;

	public Perfile() {
	}

	public int getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPerfil;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Perfile))
			return false;
		Perfile other = (Perfile) obj;
		if (idPerfil != other.idPerfil)
			return false;
		return true;
	}
	
	

}