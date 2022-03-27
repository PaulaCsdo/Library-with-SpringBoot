package com.ite.paulacasadogarcia.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ite.paulacasadogarcia.modelo.bean.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
	
	@Query("select l from Libro l where l.novedad='N'")
	public List<Libro> listarNovedad();
	
	@Query("select l from Libro l where l.titulo like %?1%")
	public List<Libro> buscarPorTitulo(String titulo);
	
	@Query("select l from Libro l where l.tema.abreviatura like %?1%")
	public List<Libro> buscarPorTema(String abreviatura);
	
	@Query("select l from Libro l where l.isbn= ?1")
	public Libro verDetalle(long isbn);

}
