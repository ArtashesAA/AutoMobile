package com.v1.automobile.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.v1.automobile.entidad.Coche;
import com.v1.automobile.entidad.Imagen;
import com.v1.automobile.servicio.CocheServicio;
import com.v1.automobile.servicio.ImagenServicio;

@RestController
@RequestMapping("/api/v1/coche")
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
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<Coche>> getAllCoches() {
		return cocheServicio.getAllCoches();
	}

	/*
	 * Recupera un coche por id. Puede acceder cualquier rol
	 * 
	 * @Parameter id de coche que se va a buscar
	 * 
	 * @return recupera el coche por id
	 */
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<Coche> getCocheById(@PathVariable Long id) {
		return cocheServicio.getCocheById(id);
	}

	/*
	 * Añade un coche a la bbdd. Puede acceder solo el admin
	 * 
	 * @Parameter coche que se va a añadir
	 * 
	 * @return guarda el coche pasado por parámetro
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Coche> addCoche(@RequestBody Coche coche) {
		return cocheServicio.addCoche(coche);
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
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Coche> updateCoche(@PathVariable Long id, @RequestBody Coche nuevoCoche) {
		return cocheServicio.updateCoche(id, nuevoCoche);
	}

	/*
	 * Borra un coche a la bbdd. Puede acceder solo el admin
	 * 
	 * @Parameter id del coche que se quiere borrar
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCoche(@PathVariable Long id) {
		return cocheServicio.deleteCoche(id);
	}

//-------------------------------------------------- Imagenes ---------------------------------------------------------

	/*
	 * Obtiene todas las imágenes asociadas a un coche por su ID. Puede acceder
	 * cualquier rol.
	 *
	 * @param id ID del coche del cual se quieren obtener las imágenes.
	 * 
	 * @return ResponseEntity con la lista de imágenes o un mensaje de error si no
	 * se encuentran imágenes.
	 */
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/imagen/{id}")
	public ResponseEntity<List<Imagen>> getAllImagenesByCocheId(@PathVariable Long cocheId) {
		return imagenServicio.getImagenesByCocheId(cocheId);
	}

	/*
	 * Obtiene una imagen específica por su ID.
	 * 
	 * @param imagenId ID de la imagen que se desea obtener.
	 * 
	 * @return ResponseEntity con la imagen solicitada o un mensaje de error si no
	 * se encuentra la imagen.
	 */
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}/imagen/{imagenId}")
	public ResponseEntity<Imagen> getImagenByCocheIdAndImagenId(@PathVariable Long imagenId) {
		return imagenServicio.getImagenById(imagenId);
	}

	/*
	 * Añade una imagen a un coche. Puede acceder solo el admin
	 * 
	 * @Parameter imagen que se va a añadir
	 * 
	 * @return añade la imagen al coche pasado por parámetro
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/imagen")
	public ResponseEntity<Imagen> addImagen(@RequestBody Imagen imagen) {
		return imagenServicio.addImagen(imagen);
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
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{imagenId}")
	public ResponseEntity<Imagen> updateImagen(@PathVariable Long imagenId, @RequestBody Imagen nuevaImagen) {
		return imagenServicio.updateImagen(imagenId, nuevaImagen);
	}

	/*
	 * Borra una imagen de un coche. Puede acceder solo el admin
	 * 
	 * @Parameter id del coche al que pertenece la imagen
	 * 
	 * @Parameter id de la imagen que se quiere borrar
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{imagenId}")
	public ResponseEntity<String> deleteImagen(@PathVariable Long imagenId) {
		return imagenServicio.deleteImagen(imagenId);
	}

}