package com.ite.paulacasadogarcia.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.paulacasadogarcia.modelo.bean.Tema;
import com.ite.paulacasadogarcia.modelo.repository.TemaRepository;

@Service
public class TemaImpl implements TemaInt{
	
	@Autowired
	private TemaRepository trepo;

	@Override
	public List<Tema> findAll() {
		return trepo.findAll();
	}

}
