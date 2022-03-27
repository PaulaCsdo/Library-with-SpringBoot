package com.ite.paulacasadogarcia.modelo.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the temas database table.
 * 
 */
@Entity
@Table(name="temas")
@NamedQuery(name="Tema.findAll", query="SELECT t FROM Tema t")
public class Tema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TEMA")
	private int idTema;

	private String abreviatura;

	@Column(name="DESC_TEMA")
	private String descTema;

	//bi-directional many-to-one association to Libro
	@OneToMany(mappedBy="tema")
	private List<Libro> libros;

	public Tema() {
	}

	public int getIdTema() {
		return this.idTema;
	}

	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}

	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getDescTema() {
		return this.descTema;
	}

	public void setDescTema(String descTema) {
		this.descTema = descTema;
	}

	public List<Libro> getLibros() {
		return this.libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public Libro addLibro(Libro libro) {
		getLibros().add(libro);
		libro.setTema(this);

		return libro;
	}

	public Libro removeLibro(Libro libro) {
		getLibros().remove(libro);
		libro.setTema(null);

		return libro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTema;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Tema))
			return false;
		Tema other = (Tema) obj;
		if (idTema != other.idTema)
			return false;
		return true;
	}
	
	

}