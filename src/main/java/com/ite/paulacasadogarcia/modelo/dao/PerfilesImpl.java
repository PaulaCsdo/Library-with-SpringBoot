package com.ite.paulacasadogarcia.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.paulacasadogarcia.modelo.bean.Perfile;
import com.ite.paulacasadogarcia.modelo.repository.PerfilesRepository;

@Service
public class PerfilesImpl implements PerfilesInt{
	
	@Autowired
	private PerfilesRepository prepo;

	@Override
	public List<Perfile> findAll() {
		return prepo.findAll();
	}

	@Override
	public Perfile findById(int idPerfil) {
		return prepo.findById(idPerfil).orElse(null);
	}

}
