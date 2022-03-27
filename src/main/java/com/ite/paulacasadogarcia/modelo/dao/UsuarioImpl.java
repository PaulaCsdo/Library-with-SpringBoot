package com.ite.paulacasadogarcia.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.paulacasadogarcia.modelo.bean.Usuario;
import com.ite.paulacasadogarcia.modelo.repository.UsuarioRepository;

@Service
public class UsuarioImpl implements UsuarioInt{
	
	@Autowired
	private UsuarioRepository urepo;

	@Override
	public List<Usuario> findAll() {
		return urepo.findAll();
	}

	@Override
	public int alta(Usuario usuario) {
		if(findById(usuario.getUsername())==null) {
			urepo.save(usuario);
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public Usuario login(String username, String password) {
		Usuario usuario= new Usuario();
		boolean flag=false;
		
		for (Usuario ele: findAll()) {
			if ((ele.getUsername().equals(username))&& ele.getPassword().equals(password)){
				flag=true;
				usuario=ele;
			}
		}
		if (flag!=true){
			return null;
		}
		return usuario;
	}

	@Override
	public Usuario findById(String username) {
		return urepo.findById(username).orElse(null);
	}
	

}
