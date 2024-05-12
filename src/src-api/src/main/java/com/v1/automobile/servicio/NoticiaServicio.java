package com.v1.automobile.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.v1.automobile.entidad.Noticia;
import com.v1.automobile.entidad.dto.NoticiaDTO;
import com.v1.automobile.repositorio.NoticiaRepositorio;

@Service
public class NoticiaServicio {

	@Autowired
	private NoticiaRepositorio noticiaRepositorio;

	public NoticiaDTO obtenerNoticiaPorId(Long id) {
		 Optional<Noticia> optionalNoticia = noticiaRepositorio.findById(id);
		    return optionalNoticia.map(this::convertToDto).orElse(null);
	}

	public List<NoticiaDTO> obtenerTodasLasNoticias() {
		List<Noticia> noticias = noticiaRepositorio.findAll();
		return noticias.stream().map(this::convertToDto).collect(Collectors.toList());
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

	private NoticiaDTO convertToDto(Noticia noticia) {
		NoticiaDTO dto = new NoticiaDTO();

		dto.setId(noticia.getId());
		dto.setFecha(noticia.getFecha());
		dto.setTitulo(noticia.getTitulo());
		dto.setContenido(noticia.getContenido());
		dto.setUrl_imagen(noticia.getUrl_imagen());
		dto.setUrl_video(noticia.getUrl_video());
		return dto;
	}
}