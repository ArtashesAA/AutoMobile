package com.v1.automobile.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v1.automobile.entidad.Noticia;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, Long>{

}
