package com.v1.automobile.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.v1.automobile.entidad.Noticia;
import com.v1.automobile.entidad.Usuario;
import com.v1.automobile.repositorio.NoticiaRepositorio;
import com.v1.automobile.repositorio.UsuarioRepositorio;

@Service
public class NoticiaServicio {

	@Autowired
	private NoticiaRepositorio noticiaRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	public Optional<Noticia> obtenerNoticiaPorId(Long id) {
		return noticiaRepositorio.findById(id);
	}

	public List<Noticia> obtenerNoticias() {
		return noticiaRepositorio.findAll();
	}

	public ResponseEntity<String> crearNoticia(Noticia noticia) {
		try {
			// Obtener el usuario actualmente autenticado
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String email = authentication.getName();
			Usuario usuario = usuarioRepositorio.findByEmail(email)
					.orElseThrow(() -> new RuntimeException("Usuario no autenticado o no encontrado"));

			// Asignar el usuario a la noticia
			noticia.setUsuario(usuario);

			noticiaRepositorio.save(noticia);

			return new ResponseEntity<>("Noticia creada correctamente", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al crear la noticia: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
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