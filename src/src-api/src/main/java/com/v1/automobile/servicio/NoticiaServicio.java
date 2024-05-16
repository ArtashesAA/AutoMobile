package com.v1.automobile.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.v1.automobile.entidad.Noticia;
import com.v1.automobile.repositorio.NoticiaRepositorio;

@Service
public class NoticiaServicio {

	@Autowired
	private NoticiaRepositorio noticiaRepositorio;

	public Optional<Noticia> obtenerNoticiaPorId(Long id) {
		return noticiaRepositorio.findById(id);
	}

	public List<Noticia> obtenerTodasLasNoticias() {
		return noticiaRepositorio.findAll();
	}

	public Noticia crearNoticia(Noticia noticia) {
		return noticiaRepositorio.save(noticia);
	}

	public ResponseEntity<String> actualizarNoticia(Long id, Noticia noticiaActualizada) {
		Optional<Noticia> optionalNoticia = noticiaRepositorio.findById(id);
		if (optionalNoticia.isPresent()) {
			Noticia noticiaExistente = optionalNoticia.get();
			// Actualizar los campos necesarios
			noticiaExistente.setTitulo(noticiaActualizada.getTitulo());
			noticiaExistente.setContenido(noticiaActualizada.getContenido());
			noticiaExistente.setUrl_imagen(noticiaActualizada.getUrl_imagen());
			noticiaExistente.setUrl_video(noticiaActualizada.getUrl_video());
			// Guardar y devolver la noticia actualizada
			noticiaRepositorio.save(noticiaExistente);
			return ResponseEntity.ok().body("Noticia con ID " + id + " actualizada correctamente");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<String> borrarNoticiaPorId(Long id) {
		try {
			noticiaRepositorio.deleteById(id);
			return new ResponseEntity<>("Noticia eliminada correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al eliminar la noticia", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}