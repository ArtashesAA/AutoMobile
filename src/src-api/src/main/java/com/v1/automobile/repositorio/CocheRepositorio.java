package com.v1.automobile.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v1.automobile.entidad.Coche;

@Repository
public interface CocheRepositorio extends JpaRepository<Coche, Long> {

}
