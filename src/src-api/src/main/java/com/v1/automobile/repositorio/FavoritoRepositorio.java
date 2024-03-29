package com.v1.automobile.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.v1.automobile.entidad.Favorito;

public interface FavoritoRepositorio extends JpaRepository<Favorito, Long> {
	long deleteByCocheId(Long cocheId);
}
