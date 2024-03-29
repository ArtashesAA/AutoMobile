package com.v1.automobile.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.v1.automobile.entidad.Imagen;
import com.v1.automobile.entidad.ImagenDTO;
import com.v1.automobile.repositorio.ImagenRepositorio;

@Service
public class ImagenServicio {
	@Autowired
	private ImagenRepositorio imagenRepositorio;

	public List<ImagenDTO> getAllImagenes() {
		List<Imagen> imagenes = imagenRepositorio.findAll();
		return imagenes.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public ResponseEntity<ImagenDTO> getImagenById(Long id) {
		Optional<Imagen> imagenOptional = imagenRepositorio.findById(id);
		if (imagenOptional.isPresent()) {
			Imagen imagen = imagenOptional.get();
			ImagenDTO imagenDTO = convertToDto(imagen);
			return ResponseEntity.ok(imagenDTO);
		} else {
			return ResponseEntity.notFound().build();
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

	public ResponseEntity<String> updateImagen(Long imagenId, Imagen nuevaImagen) {
		Optional<Imagen> optionalImagen = imagenRepositorio.findById(imagenId);

		if (optionalImagen.isPresent()) {
			Imagen imagenExistente = optionalImagen.get();
			// Actualizar solo los campos que sean modificables
			if (nuevaImagen.getImagen_url() != null) {
				imagenExistente.setImagen_url(nuevaImagen.getImagen_url());
			}

			imagenRepositorio.save(imagenExistente);
			return ResponseEntity.ok().body("Imagen con ID " + imagenId + " actualizado correctamente");
		} else {
			return ResponseEntity.notFound().build();
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

	private ImagenDTO convertToDto(Imagen imagen) {
		ImagenDTO imagenDTO = new ImagenDTO();
		imagenDTO.setImagen_url(imagen.getImagen_url());
		return imagenDTO;
	}

}
