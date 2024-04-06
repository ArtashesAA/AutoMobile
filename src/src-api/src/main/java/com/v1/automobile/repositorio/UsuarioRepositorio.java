package com.v1.automobile.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v1.automobile.entidad.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByEmail(String email);

	boolean existsById(Long id);

	void deleteById(Long id);

	Optional<Usuario> findById(Long id);

}
