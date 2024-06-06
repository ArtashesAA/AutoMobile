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

import com.v1.automobile.entidad.Coche;
import com.v1.automobile.entidad.Imagen;
import com.v1.automobile.servicio.CocheServicio;
import com.v1.automobile.servicio.ImagenServicio;

@RestController
@RequestMapping("/api/v1")
public class CocheControlador {

	@Autowired
	private CocheServicio cocheServicio;

	@Autowired
	private ImagenServicio imagenServicio;

	/*
	 * Recupera todos los coches. Puede acceder cualquier rol
	 * 
	 * @return recupera todos los coches
	 */
	@GetMapping("/public/coche")
	public ResponseEntity<List<Coche>> obtenerCoches() {
		List<Coche> coches = cocheServicio.obtenerCoches();
		return ResponseEntity.ok(coches);
	}

	/*
	 * Recupera un coche por id. Puede acceder cualquier rol
	 * 
	 * @Parameter id de coche que se va a buscar
	 * 
	 * @return recupera el coche por id
	 */
	@GetMapping("/public/coche/{id}")
	public ResponseEntity<Optional<Coche>> obtenerCochePorId(@PathVariable Long id) {
		return cocheServicio.obtenerCochePorId(id);
	}

	/*
	 * Recupera los coches por id del usuario. Puede acceder admin o usuario
	 * 
	 * @Parameter id de usuario del cual se quiere recuperar los coches
	 * 
	 * @return recupera los coches por id del usuario
	 */
	@GetMapping("/adminuser/coche/usuario/{idUsuario}")
	public ResponseEntity<List<Coche>> obtenerCochesPorIdUsuario(@PathVariable Long idUsuario) {
		List<Coche> coches = cocheServicio.obtenerCochesPorIdUsuario(idUsuario);
		return ResponseEntity.ok(coches);
	}

	/*
	 * Recupera los coches favoritos del usuario por su id. Puede acceder admin o
	 * usuario
	 * 
	 * @Parameter id de usuario del cual se quiere recuperar los coches
	 * 
	 * @return recupera los coches favoritos por id del usuario
	 */
	@GetMapping("/adminuser/coche/favorito/usuario/{idUsuario}")
	public ResponseEntity<List<Coche>> obtenerCochesFavoritosPorIdUsuario(@PathVariable Long idUsuario) {
		List<Coche> coches = cocheServicio.obtenerCochesFavoritosPorIdUsuario(idUsuario);
		return ResponseEntity.ok(coches);
	}

	/*
	 * Añade un coche a la bbdd. Puede acceder un admin o usuario
	 * 
	 * @Parameter coche que se va a añadir
	 * 
	 * @return guarda el coche pasado por parámetro
	 */
	@PostMapping("/adminuser/coche")
	public ResponseEntity<String> crearCoche(@RequestBody Coche request) {
		return cocheServicio.crearCoche(request);
	}

	/*
	 * Actualiza un coche de la bbdd. Puede acceder solo el admin
	 * 
	 * @Parameter id del coche que se quiere actualizar
	 * 
	 * @Parameter nuevoCoche que contiene los datos del coche nuevo que va a
	 * sustituir al otro
	 * 
	 * @return actualiza el coche pasado por parámetro
	 */
	@PutMapping("/admin/coche/{id}")
	public ResponseEntity<String> actualizarCoche(@PathVariable Long id, @RequestBody Coche nuevoCoche) {
		return cocheServicio.actualizarCoche(id, nuevoCoche);
	}

	/*
	 * Actualiza un coche de la bbdd. Este coche debe ser creado por el usuario que
	 * lo actualiza. Puede acceder admin o usuario
	 * 
	 * @Parameter id del coche que se quiere actualizar
	 * 
	 * @Parameter nuevoCoche que contiene los datos del coche nuevo que va a
	 * sustituir al otro
	 * 
	 * @return actualiza el coche pasado por parámetro
	 */
	@PutMapping("/adminuser/coche/{id}")
	public ResponseEntity<String> actualizarCochePropio(@PathVariable Long id, @RequestBody Coche nuevoCoche) {
		return cocheServicio.actualizarCochePropio(id, nuevoCoche);
	}

	/*
	 * Borra un coche a la bbdd. Puede acceder solo el admin
	 * 
	 * @Parameter id del coche que se quiere borrar
	 */
	@DeleteMapping("/admin/coche/{id}")
	public ResponseEntity<String> borrarCoche(@PathVariable Long id) {
		System.out.println("Eliminando");
		return cocheServicio.borrarCoche(id);
	}

	/*
	 * Borra un coche a la bbdd. Este coche debe ser creado por el usuario que lo
	 * borra. Puede acceder admin o usuario
	 * 
	 * @Parameter id del coche que se quiere borrar
	 */
	@DeleteMapping("/adminuser/coche/{id}")
	public ResponseEntity<String> borrarCochePropio(@PathVariable Long id) {
		System.out.println("Eliminando");
		return cocheServicio.borrarCochePropio(id);
	}

//-------------------------------------------------- Imagenes ---------------------------------------------------------

	/*
	 * Recupera todos las imagenes. Puede acceder cualquier rol
	 * 
	 * @return recupera todas las imagenes
	 */
	@GetMapping("/public/imagen")
	public ResponseEntity<List<Imagen>> obtenerImagenes() {
		List<Imagen> imagenes = imagenServicio.obtenerImagenes();
		return ResponseEntity.ok(imagenes);
	}

	/*
	 * Recupera una imagen por id. Puede acceder cualquier rol
	 * 
	 * @Parameter id de imagen que se va a buscar
	 * 
	 * @return recupera la imagen por id
	 */
	@GetMapping("/public/imagen/{id}")
	public ResponseEntity<Optional<Imagen>> obtenerImagenPorId(@PathVariable Long id) {
		return imagenServicio.obtenerImagenPorId(id);
	}

	/*
	 * Recupera imagenes por id de coche. Puede acceder cualquier rol
	 * 
	 * @Parameter id de coche que se va a buscar
	 * 
	 */
	@GetMapping("/public/imagen/coche/{id}")
	public ResponseEntity<List<Imagen>> obtenerImagenesPorCocheId(@PathVariable Long id) {
		return (ResponseEntity<List<Imagen>>) imagenServicio.obtenerImagenesPorCocheId(id);
	}

	/*
	 * Añade una imagen a un coche. Puede acceder admin y usuario
	 * 
	 * @Parameter imagen que se va a añadir
	 * 
	 * @return añade la imagen al coche pasado por parámetro
	 */
	@PostMapping("/adminuser/imagen")
	public ResponseEntity<String> crearImagen(@RequestBody Imagen imagen) {
		return imagenServicio.crearImagen(imagen.getId(), imagen.getImagen_url());
	}

	/*
	 * Actualiza una imagen asociada a un coche por su ID. Puede acceder solo el
	 * admin.
	 *
	 * @param cocheId ID del coche al que está asociada la imagen.
	 * 
	 * @param imagenId ID de la imagen que se desea actualizar.
	 * 
	 * @param nuevaImagen Nuevos datos de la imagen a actualizar.
	 * 
	 * @return ResponseEntity con la imagen actualizada o un mensaje de error si no
	 * se encuentra la imagen.
	 */
	@PutMapping("/admin/imagen/{imagenId}")
	public ResponseEntity<String> actualizarImagen(@PathVariable Long imagenId, @RequestBody Imagen nuevaImagen) {
		return imagenServicio.actualizarImagen(imagenId, nuevaImagen);
	}

	/*
	 * Actualiza una imagen asociada a un coche por su ID. Solo podrá actualizar si
	 * la imagen pertenece al usuario que lo actualiza. Puede acceder admin o
	 * usuario.
	 *
	 * @param cocheId ID del coche al que está asociada la imagen.
	 * 
	 * @param imagenId ID de la imagen que se desea actualizar.
	 * 
	 * @param nuevaImagen Nuevos datos de la imagen a actualizar.
	 * 
	 * @return ResponseEntity con la imagen actualizada o un mensaje de error si no
	 * se encuentra la imagen.
	 */
	@PutMapping("/adminuser/imagen/{imagenId}")
	public ResponseEntity<String> actualizarImagenPropio(@PathVariable Long imagenId, @RequestBody Imagen nuevaImagen) {
		return imagenServicio.actualizarImagenPropio(imagenId, nuevaImagen);
	}

	/*
	 * Borra una imagen de un coche. Puede acceder solo el admin
	 * 
	 * @Parameter id del coche al que pertenece la imagen
	 * 
	 * @Parameter id de la imagen que se quiere borrar
	 */
	@DeleteMapping("/admin/imagen/{imagenId}")
	public ResponseEntity<String> borrarImagen(@PathVariable Long imagenId) {
		return imagenServicio.borrarImagen(imagenId);
	}

	/*
	 * Borra una imagen de un coche. Solo podrá borrar si la imagen pertenece al
	 * usuario que lo borra. Puede acceder admin o usuario.
	 * 
	 * @Parameter id del coche al que pertenece la imagen
	 * 
	 * @Parameter id de la imagen que se quiere borrar
	 */
	@DeleteMapping("/adminuser/imagen/{imagenId}")
	public ResponseEntity<String> borrarImagenPropio(@PathVariable Long imagenId) {
		return imagenServicio.borrarImagenPropio(imagenId);
	}

}