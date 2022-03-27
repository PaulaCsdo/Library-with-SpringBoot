package com.ite.paulacasadogarcia.modelo.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the libros database table.
 * 
 */
@Entity
@Table(name="libros")
@NamedQuery(name="Libro.findAll", query="SELECT l FROM Libro l")
public class Libro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long isbn;

	private String autor;

	private String novedad;

	private int paginas;

	@Column(name="PRECIO_UNITARIO")
	private BigDecimal precioUnitario;

	private String titulo;

	//bi-directional many-to-one association to Tema
	@ManyToOne
	@JoinColumn(name="ID_TEMA")
	private Tema tema;

	//bi-directional many-to-one association to LineasPedido
	@OneToMany(mappedBy="libro")
	private List<LineasPedido> lineasPedidos;

	public Libro() {
	}

	public long getIsbn() {
		return this.isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getNovedad() {
		return this.novedad;
	}

	public void setNovedad(String novedad) {
		this.novedad = novedad;
	}

	public int getPaginas() {
		return this.paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public BigDecimal getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Tema getTema() {
		return this.tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public List<LineasPedido> getLineasPedidos() {
		return this.lineasPedidos;
	}

	public void setLineasPedidos(List<LineasPedido> lineasPedidos) {
		this.lineasPedidos = lineasPedidos;
	}

	public LineasPedido addLineasPedido(LineasPedido lineasPedido) {
		getLineasPedidos().add(lineasPedido);
		lineasPedido.setLibro(this);

		return lineasPedido;
	}

	public LineasPedido removeLineasPedido(LineasPedido lineasPedido) {
		getLineasPedidos().remove(lineasPedido);
		lineasPedido.setLibro(null);

		return lineasPedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (isbn ^ (isbn >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Libro))
			return false;
		Libro other = (Libro) obj;
		if (isbn != other.isbn)
			return false;
		return true;
	}
	
	

}