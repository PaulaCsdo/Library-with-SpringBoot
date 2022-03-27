package com.ite.paulacasadogarcia.modelo.dao;

import java.util.List;

import com.ite.paulacasadogarcia.modelo.bean.Usuario;

public interface UsuarioInt {

	List<Usuario> findAll();
	Usuario findById(String username);
	int alta(Usuario usuario);
	Usuario login(String username, String password);
	
}
