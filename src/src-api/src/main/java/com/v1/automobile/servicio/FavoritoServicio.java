package com.v1.automobile.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.v1.automobile.entidad.Favorito;
import com.v1.automobile.repositorio.FavoritoRepositorio;

@Service
public class FavoritoServicio {

	@Autowired
	private FavoritoRepositorio favoritoRepositorio;

	public Optional<Favorito> obtenerFavoritoPorId(Long id) {
		return favoritoRepositorio.findById(id);
	}

	public List<Favorito> obtenerFavoritos() {
		return favoritoRepositorio.findAll();
	}

	public Favorito crearFavorito(Favorito favorito) {
		return favoritoRepositorio.save(favorito);
	}

	public ResponseEntity<String> borrarFavoritoPorId(Long id) {
		try {
			favoritoRepositorio.deleteById(id);
			return new ResponseEntity<>("Favorito eliminado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al eliminar el favorito", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
