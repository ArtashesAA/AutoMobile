package com.v1.automobile.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1")
public class NoticiaControlador {
	@Autowired
	private NoticiaServicio noticiaServicio;

	/*
	 * Recupera todas las noticias. Puede acceder un admin o usuario
	 * 
	 * @return recupera todas las noticias
	 */
	@GetMapping("/adminuser/noticia")
	public ResponseEntity<List<Noticia>> obtenerNoticias() {
		List<Noticia> noticias = (List<Noticia>) noticiaServicio.obtenerNoticias();
		return ResponseEntity.ok().body(noticias);
	}

	/*
	 * Recupera una noticia por id. Puede acceder un admin o usuario
	 * 
	 * @Parameter id de noticia que se va a buscar
	 * 
	 * @return recupera la noticia
	 */
	@GetMapping("/adminuser/noticia/{id}")
	public ResponseEntity<Optional<Noticia>> obtenerNoticiaPorId(@PathVariable Long id) {
		Optional<Noticia> noticia = noticiaServicio.obtenerNoticiaPorId(id);
		if (noticia != null) {
			return ResponseEntity.ok().body(noticia);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * Añade una noticia a la bbdd. Puede acceder solo un admin
	 * 
	 * @Parameter noticia que se va a crear
	 * 
	 * @return recupera la noticia guardada
	 */
	@PostMapping("/admin/noticia")
	public ResponseEntity<String> crearNoticia(@RequestBody Noticia noticia) {
		return noticiaServicio.crearNoticia(noticia);
	}

	/*
	 * Actualiza una noticia de la bbdd. Puede acceder solo el admin
	 * 
	 * @Parameter id de la noticia que se quiere actualizar
	 * 
	 * @Parameter noticiaActualizada que contiene los datos de la noticia que va a
	 * sustituir a la antigua
	 * 
	 * @return Recupera la noticia
	 */
	@PutMapping("/admin/noticia/{id}")
	public ResponseEntity<String> actualizarNoticia(@PathVariable Long id, @RequestBody Noticia noticiaActualizada) {
		return noticiaServicio.actualizarNoticia(id, noticiaActualizada);
	}

	/*
	 * Borra una noticia de la bbdd. Puede acceder solo el admin
	 * 
	 * @Parameter id de la noticia que se quiere borrar
	 */
	@DeleteMapping("/admin/noticia/{id}")
	public ResponseEntity<String> borrarNoticiaPorId(@PathVariable Long id) {
		return noticiaServicio.borrarNoticiaPorId(id);
	}
}
