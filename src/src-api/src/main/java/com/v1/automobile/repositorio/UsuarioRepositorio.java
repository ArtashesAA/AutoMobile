package com.v1.automobile.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.v1.automobile.entidad.Usuario;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByEmail(String email);
	
}
