package com.ite.paulacasadogarcia.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.paulacasadogarcia.modelo.bean.Libro;
import com.ite.paulacasadogarcia.modelo.repository.LibroRepository;

@Service
public class LibroImpl implements LibroInt{
	
	@Autowired
	private LibroRepository lirepo;

	@Override
	public List<Libro> findAll() {
		return lirepo.findAll();
	}

	@Override
	public int editar(Libro libro) {
		if(findById(libro.getIsbn())!=null) {
			lirepo.save(libro);
			return 1;
		}
		else{
			return 0;
		}
	}

	@Override
	public int eliminar(long isbn) {
		int filas=0;
		try {
			lirepo.deleteById(isbn);
			filas=1;
		}catch (Exception e) {
			e.printStackTrace();
	
		}
		return filas;
		
	}

	@Override
	public List<Libro> listarNovedad() {
		return lirepo.listarNovedad();
	}

	@Override
	public List<Libro> buscarPorTitulo(String titulo) {
		return lirepo.buscarPorTitulo(titulo);
	}

	@Override
	public List<Libro> buscarPorTema(String abreviatura) {
		return lirepo.buscarPorTema(abreviatura);
	}

	@Override
	public Libro findById(long isbn) {
		return lirepo.findById(isbn).orElse(null);
	}

}
