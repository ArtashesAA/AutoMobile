package com.v1.automobile.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.v1.automobile.entidad.Coche;


public interface CocheRepositorio extends JpaRepository<Coche, Long> {

}
