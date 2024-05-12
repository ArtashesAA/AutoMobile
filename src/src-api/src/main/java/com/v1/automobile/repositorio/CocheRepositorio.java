package com.v1.automobile.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v1.automobile.entidad.Coche;

@Repository
public interface CocheRepositorio extends JpaRepository<Coche, Long> {
	List<Coche> findByMarcaAndModeloAndAnyoAndPrecio(String marca, String modelo, Integer anyo, Integer precio);

	List<Coche> findByMarca(String marca);

	List<Coche> findByMarcaAndModelo(String marca, String modelo);

}
