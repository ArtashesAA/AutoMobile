package com.v1.automobile.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.v1.automobile.entidad.Imagen;
import com.v1.automobile.repositorio.ImagenRepositorio;

@Service
public class ImagenServicio {
	@Autowired
	private ImagenRepositorio imagenRepositorio;

	public ResponseEntity<List<Imagen>> getAllImagenes() {
		List<Imagen> imagenes = imagenRepositorio.findAll();
		return new ResponseEntity<>(imagenes, HttpStatus.OK);
	}

	public ResponseEntity<Imagen> getImagenById(Long id) {
		Imagen imagen = imagenRepositorio.findById(id).orElse(null);
		if (imagen != null) {
			return new ResponseEntity<>(imagen, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<Imagen>> getImagenesByCocheId(Long cocheId) {
		List<Imagen> imagenes = imagenRepositorio.getImagenesByCocheId(cocheId);
		if (!imagenes.isEmpty()) {
			return new ResponseEntity<>(imagenes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Imagen> addImagen(Imagen imagen) {
		Imagen nuevaImagen = imagenRepositorio.save(imagen);
		return new ResponseEntity<>(nuevaImagen, HttpStatus.CREATED);
	}

	public ResponseEntity<Imagen> updateImagen(Long imagenId, Imagen nuevaImagen) {
		Optional<Imagen> optionalImagen = imagenRepositorio.findById(imagenId);

		if (optionalImagen.isPresent()) {
			Imagen imagenExistente = optionalImagen.get();
			// Actualizar solo los campos que sean modificables
			if (nuevaImagen.getImageUrl() != null) {
				imagenExistente.setImageUrl(nuevaImagen.getImageUrl());
			}

			Imagen imagenActualizada = imagenRepositorio.save(imagenExistente);
			return new ResponseEntity<>(imagenActualizada, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> deleteImagen(Long id) {
		try {
			imagenRepositorio.deleteById(id);
			return new ResponseEntity<>("Imagen con ID " + id + " borrada correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al borrar la imagen con ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
