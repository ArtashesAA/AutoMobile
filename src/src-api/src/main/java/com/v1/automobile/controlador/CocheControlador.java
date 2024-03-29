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
import com.v1.automobile.entidad.CocheDTO;
import com.v1.automobile.entidad.CocheRequest;
import com.v1.automobile.entidad.Imagen;
import com.v1.automobile.entidad.ImagenDTO;
import com.v1.automobile.entidad.ImagenRequest;
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
	public ResponseEntity<List<CocheDTO>> getAllCoches() {
		List<CocheDTO> coches = cocheServicio.getAllCoches();
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
	public ResponseEntity<CocheDTO> getCocheById(@PathVariable Long id) {
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
	@PostMapping("/coche")
	public ResponseEntity<String> addCoche(@RequestBody CocheRequest request) {
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
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/coche/{id}")
	public ResponseEntity<String> updateCoche(@PathVariable Long id, @RequestBody Coche nuevoCoche) {
		return cocheServicio.updateCoche(id, nuevoCoche);
	}

	/*
	 * Borra un coche a la bbdd. Puede acceder solo el admin
	 * 
	 * @Parameter id del coche que se quiere borrar
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/coche/{id}")
	public ResponseEntity<String> deleteCoche(@PathVariable Long id) {
		return cocheServicio.deleteCoche(id);
	}

//-------------------------------------------------- Imagenes ---------------------------------------------------------

	/*
	 * Recupera todos las imagenes. Puede acceder cualquier rol
	 * 
	 * @return recupera todas las imagenes
	 */
	@GetMapping("/public/imagen")
	public ResponseEntity<List<ImagenDTO>> getAllImagenes() {
		List<ImagenDTO> imagenes = imagenServicio.getAllImagenes();
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
	public ResponseEntity<ImagenDTO> getImagenById(@PathVariable Long id) {
		return imagenServicio.getImagenById(id);
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
	public ResponseEntity<String> addImagen(@RequestBody ImagenRequest request) {
        return imagenServicio.addImagen(request.getCoche_id(), request.getImagen_url());
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
	@PutMapping("/imagen/{imagenId}")
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
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/imagen/{imagenId}")
	public ResponseEntity<String> deleteImagen(@PathVariable Long imagenId) {
		return imagenServicio.deleteImagen(imagenId);
	}

}