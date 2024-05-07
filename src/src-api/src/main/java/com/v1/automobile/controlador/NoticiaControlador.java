package com.v1.automobile.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v1.automobile.entidad.Noticia;
import com.v1.automobile.entidad.dto.NoticiaDTO;
import com.v1.automobile.servicio.NoticiaServicio;

@RestController
@RequestMapping("/api/v1/noticia")
public class NoticiaControlador {
	@Autowired
	private NoticiaServicio noticiaServicio;

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping("/{id}")
	public ResponseEntity<NoticiaDTO> obtenerNoticiaPorId(@PathVariable Long id) {
		NoticiaDTO noticia = noticiaServicio.obtenerNoticiaPorId(id);
		if (noticia != null) {
			return ResponseEntity.ok().body(noticia);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping
	public ResponseEntity<List<NoticiaDTO>> obtenerTodasLasNoticias() {
		List<NoticiaDTO> noticias = noticiaServicio.obtenerTodasLasNoticias();
		return ResponseEntity.ok().body(noticias);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Noticia> crearNoticia(@RequestBody Noticia noticia) {
		Noticia nuevaNoticia = noticiaServicio.crearNoticia(noticia);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevaNoticia);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<String> actualizarNoticia(@PathVariable Long id, @RequestBody Noticia noticiaActualizada) {
		return noticiaServicio.actualizarNoticia(id, noticiaActualizada);	
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> borrarNoticiaPorId(@PathVariable Long id) {
		
		return noticiaServicio.borrarNoticiaPorId(id);
	}
}
