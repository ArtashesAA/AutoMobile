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

import com.v1.automobile.entidad.Coche;
import com.v1.automobile.repositorio.CocheRepositorio;


@RestController
@RequestMapping("/api/v1/coche")
public class CocheControlador {

	@Autowired
	private CocheRepositorio cocheRepositorio;

	/*
	 * Recupera todos los coches. Puede acceder cualquier rol
	 * 
	 * @return recupera todos los coches
	 */
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<Coche>> getAllCoches() {
		List<Coche> coches = cocheRepositorio.findAll();
		return new ResponseEntity<>(coches, HttpStatus.OK);
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
		Coche coche = cocheRepositorio.findById(id).orElse(null);
		if (coche != null) {
			return new ResponseEntity<>(coche, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
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
		Coche nuevoCoche = cocheRepositorio.save(coche);
		return new ResponseEntity<>(nuevoCoche, HttpStatus.CREATED);
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
		Coche cocheExistente = cocheRepositorio.findById(id).orElse(null);
		if (cocheExistente != null) {
			cocheExistente.setMarca(nuevoCoche.getMarca());
			cocheExistente.setModelo(nuevoCoche.getModelo());
			cocheExistente.setAnyo(nuevoCoche.getAnyo());
			cocheExistente.setKilometraje(nuevoCoche.getKilometraje());
			cocheExistente.setPeso(nuevoCoche.getPeso());
			cocheExistente.setColor(nuevoCoche.getColor());
			cocheExistente.setPrecio(nuevoCoche.getPrecio());
			cocheExistente.setDescripcion(nuevoCoche.getDescripcion());

			Coche cocheActualizado = cocheRepositorio.save(cocheExistente);
			return new ResponseEntity<>(cocheActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * Borra un coche a la bbdd. Puede acceder solo el admin
	 * 
	 * @Parameter id del coche que se quiere borrar
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCoche(@PathVariable Long id) {
		try {
			cocheRepositorio.deleteById(id);
			return new ResponseEntity<>("Coche con ID " + id + " borrado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al borrar el coche con ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}