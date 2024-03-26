package com.v1.automobile.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.v1.automobile.entidad.Coche;
import com.v1.automobile.repositorio.CocheRepositorio;

@Service
public class CocheServicio {

	@Autowired
	private CocheRepositorio cocheRepositorio;
	
	public ResponseEntity<List<Coche>> getAllCoches() {
		List<Coche> coches = cocheRepositorio.findAll();
		return new ResponseEntity<>(coches, HttpStatus.OK);
	}

	public ResponseEntity<Coche> getCocheById(Long id) {
		Coche coche = cocheRepositorio.findById(id).orElse(null);
		if (coche != null) {
			return new ResponseEntity<>(coche, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Coche> addCoche(Coche coche) {
		Coche nuevoCoche = cocheRepositorio.save(coche);
		return new ResponseEntity<>(nuevoCoche, HttpStatus.CREATED);
	}

	public ResponseEntity<Coche> updateCoche(Long id, Coche nuevoCoche) {
		Coche cocheExistente = cocheRepositorio.findById(id).orElse(null);
		if (cocheExistente != null) {
			// Actualizar los campos del coche existente con los datos del nuevo coche
			cocheExistente.setMarca(nuevoCoche.getMarca());
			cocheExistente.setModelo(nuevoCoche.getModelo());
			cocheExistente.setAnyo(nuevoCoche.getAnyo());
			cocheExistente.setKilometraje(nuevoCoche.getKilometraje());
			cocheExistente.setPeso(nuevoCoche.getPeso());
			cocheExistente.setColor(nuevoCoche.getColor());
			cocheExistente.setPrecio(nuevoCoche.getPrecio());
			cocheExistente.setDescripcion(nuevoCoche.getDescripcion());

			// Guardar el coche actualizado
			Coche cocheActualizado = cocheRepositorio.save(cocheExistente);
			return new ResponseEntity<>(cocheActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> deleteCoche(Long id) {
		try {
			cocheRepositorio.deleteById(id);
			return new ResponseEntity<>("Coche con ID " + id + " borrado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al borrar el coche con ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
