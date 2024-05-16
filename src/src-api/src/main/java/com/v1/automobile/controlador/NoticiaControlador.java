package com.v1.automobile.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v1.automobile.entidad.Noticia;
import com.v1.automobile.servicio.NoticiaServicio;

@RestController
@RequestMapping("/api/v1/noticia")
public class NoticiaControlador {
	@Autowired
	private NoticiaServicio noticiaServicio;

	@GetMapping("/adminuser/{id}")
	public ResponseEntity<Optional<Noticia>> obtenerNoticiaPorId(@PathVariable Long id) {
		Optional<Noticia> noticia = noticiaServicio.obtenerNoticiaPorId(id);
		if (noticia != null) {
			return ResponseEntity.ok().body(noticia);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/adminuser")
	public ResponseEntity<List<Noticia>> obtenerTodasLasNoticias() {
		List<Noticia> noticias = (List<Noticia>) noticiaServicio.obtenerTodasLasNoticias();
		return ResponseEntity.ok().body(noticias);
	}

	@PostMapping("/admin")
	public ResponseEntity<Noticia> crearNoticia(@RequestBody Noticia noticia) {
		Noticia nuevaNoticia = noticiaServicio.crearNoticia(noticia);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevaNoticia);
	}

	@PutMapping("/admin/{id}")
	public ResponseEntity<String> actualizarNoticia(@PathVariable Long id, @RequestBody Noticia noticiaActualizada) {
		return noticiaServicio.actualizarNoticia(id, noticiaActualizada);	
	}

	@DeleteMapping("/admin/{id}")
	public ResponseEntity<String> borrarNoticiaPorId(@PathVariable Long id) {
		
		return noticiaServicio.borrarNoticiaPorId(id);
	}
}
