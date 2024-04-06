package com.v1.automobile.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v1.automobile.entidad.Noticia;
import com.v1.automobile.repositorio.NoticiaRepositorio;

@Service
public class NoticiaServicio {

	@Autowired
	private NoticiaRepositorio noticiaRepositorio;

	public Noticia obtenerNoticiaPorId(Long id) {
		Optional<Noticia> optionalNoticia = noticiaRepositorio.findById(id);
		return optionalNoticia.orElse(null);
	}

	public List<Noticia> obtenerTodasLasNoticias() {
		return noticiaRepositorio.findAll();
	}

	public Noticia crearNoticia(Noticia noticia) {
		return noticiaRepositorio.save(noticia);
	}

	public Noticia actualizarNoticia(Long id, Noticia noticiaActualizada) {
		Optional<Noticia> optionalNoticia = noticiaRepositorio.findById(id);
		if (optionalNoticia.isPresent()) {
			Noticia noticiaExistente = optionalNoticia.get();
			// Actualizar los campos necesarios
			noticiaExistente.setTitulo(noticiaActualizada.getTitulo());
			noticiaExistente.setContenido(noticiaActualizada.getContenido());
			noticiaExistente.setUrl_imagen(noticiaActualizada.getUrl_imagen());
			noticiaExistente.setUrl_video(noticiaActualizada.getUrl_video());
			// Guardar y devolver la noticia actualizada
			return noticiaRepositorio.save(noticiaExistente);
		} else {
			return null; 
		}
	}

	public void borrarNoticiaPorId(Long id) {
		noticiaRepositorio.deleteById(id);
	}
}