package com.ite.paulacasadogarcia.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ite.paulacasadogarcia.modelo.bean.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
