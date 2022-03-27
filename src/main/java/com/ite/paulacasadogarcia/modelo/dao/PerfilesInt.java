package com.ite.paulacasadogarcia.modelo.dao;

import java.util.List;

import com.ite.paulacasadogarcia.modelo.bean.Perfile;

public interface PerfilesInt {
	
	List <Perfile> findAll();
	Perfile findById(int idPerfil);

}
