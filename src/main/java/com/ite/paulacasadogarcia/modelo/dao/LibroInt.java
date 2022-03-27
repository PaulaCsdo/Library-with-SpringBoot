package com.ite.paulacasadogarcia.modelo.dao;

import java.util.List;

import com.ite.paulacasadogarcia.modelo.bean.Libro;

public interface LibroInt {
	
	List<Libro> findAll();
	Libro findById(long isbn);
	int editar (Libro libro);
	int eliminar (long isbn);
	List<Libro> listarNovedad();
	List<Libro> buscarPorTitulo(String titulo);
	List<Libro> buscarPorTema(String abreviatura);

}
