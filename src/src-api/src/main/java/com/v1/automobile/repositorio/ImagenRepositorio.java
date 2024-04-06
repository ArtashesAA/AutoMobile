package com.v1.automobile.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v1.automobile.entidad.Imagen;

@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, Long> {
	List<Imagen> findByCocheId(Long cocheId);

}
