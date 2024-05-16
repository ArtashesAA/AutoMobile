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
	public ResponseEntity<List<Coche>> getAllCoches() {
		List<Coche> coches = cocheServicio.getAllCoches();
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
	public ResponseEntity<Optional<Coche>> getCocheById(@PathVariable Long id) {
		return cocheServicio.getCocheById(id);
	}

	/*
	 * Añade un coche a la bbdd. Puede acceder un admin o usuario
	 * 
	 * @Parameter coche que se va a añadir
	 * 
	 * @return guarda el coche pasado por parámetro
	 */
	@PostMapping("/adminuser/coche")
	public ResponseEntity<String> addCoche(@RequestBody Coche request) {
		return cocheServicio.addCoche(request);
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
	public ResponseEntity<String> updateCoche(@PathVariable Long id, @RequestBody Coche nuevoCoche) {
		return cocheServicio.updateCoche(id, nuevoCoche);
	}

	/*
	 * Borra un coche a la bbdd. Puede acceder solo el admin
	 * 
	 * @Parameter id del coche que se quiere borrar
	 */
	@DeleteMapping("/admin/coche/{id}")
	public ResponseEntity<String> deleteCoche(@PathVariable Long id) {
		System.out.println("Eliminando");
		return cocheServicio.deleteCoche(id);
	}

//-------------------------------------------------- Imagenes ---------------------------------------------------------

	/*
	 * Recupera todos las imagenes. Puede acceder cualquier rol
	 * 
	 * @return recupera todas las imagenes
	 */
	@GetMapping("/public/imagen")
	public ResponseEntity<List<Imagen>> getAllImagenes() {
		List<Imagen> imagenes = imagenServicio.getAllImagenes();
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
	public ResponseEntity<Optional<Imagen>> getImagenById(@PathVariable Long id) {
		return imagenServicio.getImagenById(id);
	}

	/*
	 * Recupera imagenes por id de coche. Puede acceder cualquier rol
	 * 
	 * @Parameter id de coche que se va a buscar
	 * 
	 */
	@GetMapping("/public/imagen/coche/{id}")
	public ResponseEntity<List<Imagen>> getImagenByCocheId(@PathVariable Long id) {
		return (ResponseEntity<List<Imagen>>) imagenServicio.getImagenesByCocheId(id);
	}

	/*
	 * Añade una imagen a un coche. Puede acceder admin y usuario
	 * 
	 * @Parameter imagen que se va a añadir
	 * 
	 * @return añade la imagen al coche pasado por parámetro
	 */
	@PostMapping("/adminuser/imagen")
	public ResponseEntity<String> addImagen(@RequestBody Imagen imagen) {
		return imagenServicio.addImagen(imagen.getId(), imagen.getImagen_url());
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
	public ResponseEntity<String> updateImagen(@PathVariable Long imagenId, @RequestBody Imagen nuevaImagen) {
		return imagenServicio.updateImagen(imagenId, nuevaImagen);
	}

	/*
	 * Borra una imagen de un coche. Puede acceder solo el admin
	 * 
	 * @Parameter id del coche al que pertenece la imagen
	 * 
	 * @Parameter id de la imagen que se quiere borrar
	 */
	@DeleteMapping("/admin/imagen/{imagenId}")
	public ResponseEntity<String> deleteImagen(@PathVariable Long imagenId) {
		return imagenServicio.deleteImagen(imagenId);
	}

}