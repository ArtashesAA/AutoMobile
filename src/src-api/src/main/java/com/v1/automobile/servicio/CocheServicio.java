package com.v1.automobile.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.v1.automobile.entidad.Coche;
import com.v1.automobile.entidad.Imagen;
import com.v1.automobile.entidad.Usuario;
import com.v1.automobile.repositorio.CocheRepositorio;
import com.v1.automobile.repositorio.ImagenRepositorio;
import com.v1.automobile.repositorio.UsuarioRepositorio;

@Service
public class CocheServicio {

	@Autowired
	private CocheRepositorio cocheRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private ImagenRepositorio imagenRepositorio;

	public List<Coche> obtenerCoches() {
		return cocheRepositorio.findAll();
	}

	public ResponseEntity<Optional<Coche>> obtenerCochePorId(Long id) {
		Optional<Coche> cocheOptional = cocheRepositorio.findById(id);
		if (cocheOptional.isPresent()) {
			return ResponseEntity.ok(cocheOptional);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public List<Coche> obtenerCochesPorIdUsuario(Long idUsuario) {
		return cocheRepositorio.findByUsuarioId(idUsuario);
	}

	public List<Coche> obtenerCochesFavoritosPorIdUsuario(Long idUsuario) {
		return cocheRepositorio.findByFavoritosUsuarioId(idUsuario);
	}

	public ResponseEntity<String> crearCoche(Coche coche) {
		try {
			// Obtener el usuario desde el coche
			// Obtener el usuario actualmente autenticado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            Usuario usuario = usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no autenticado o no encontrado"));
            
            // Asignar el usuario al coche
            coche.setUsuario(usuario);

			// Guardar el coche en la base de datos
			Coche savedCoche = cocheRepositorio.save(coche);

			// Guarda las imágenes asociadas
			for (Imagen imagen : coche.getImagenes()) {
				imagen.setCoche(savedCoche); // Asegurar que la imagen tiene el coche asociado
				imagenRepositorio.save(imagen); // Guardar cada imagen en el repositorio de imágenes
			}

			return new ResponseEntity<>("Coche creado correctamente", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al crear el coche: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> actualizarCoche(Long id, Coche nuevoCoche) {
		Coche cocheExistente = cocheRepositorio.findById(id).orElse(null);
		if (cocheExistente != null) {
			// Actualizar los campos del coche existente con los datos del nuevo coche
			cocheExistente.setMarca(nuevoCoche.getMarca());
			cocheExistente.setModelo(nuevoCoche.getModelo());
			cocheExistente.setImagenPrincipal(nuevoCoche.getImagenPrincipal());
			cocheExistente.setPrecio(nuevoCoche.getPrecio());
			cocheExistente.setAnyo(nuevoCoche.getAnyo());
			cocheExistente.setPotencia(nuevoCoche.getPotencia());
			cocheExistente.setKilometraje(nuevoCoche.getKilometraje());
			cocheExistente.setCombustible(nuevoCoche.getCombustible());
			cocheExistente.setConsumo(nuevoCoche.getConsumo());
			cocheExistente.setTipoCambio(nuevoCoche.getTipoCambio());
			cocheExistente.setCategoria(nuevoCoche.getCategoria());
			cocheExistente.setTipoVehiculo(nuevoCoche.getTipoVehiculo());
			cocheExistente.setTraccion(nuevoCoche.getTraccion());
			cocheExistente.setPlazas(nuevoCoche.getPlazas());
			cocheExistente.setPuertas(nuevoCoche.getPuertas());
			cocheExistente.setGarantia(nuevoCoche.getGarantia());
			cocheExistente.setPeso(nuevoCoche.getPeso());
			cocheExistente.setColor(nuevoCoche.getColor());
			cocheExistente.setNumeroMarchas(nuevoCoche.getNumeroMarchas());
			cocheExistente.setNumeroCilindros(nuevoCoche.getNumeroCilindros());
			cocheExistente.setCiudad(nuevoCoche.getCiudad());
			cocheExistente.setDescripcion(nuevoCoche.getDescripcion());
			cocheExistente.setTelefonoAdjunto(nuevoCoche.getTelefonoAdjunto());
			cocheExistente.setEmailAdjunto(nuevoCoche.getEmailAdjunto());

			// Guardar el coche actualizado
			cocheRepositorio.save(cocheExistente);

			return ResponseEntity.ok().body("Coche con ID " + id + " actualizado correctamente");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<String> actualizarCochePropio(Long idCoche, Coche nuevoCoche) {
		// Obtener el usuario actualmente autenticado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String username = authentication.getName();
			Usuario usuario = usuarioRepositorio.findByEmail(username).orElse(null);
			if (usuario != null) {
				// Verificar si el coche con el ID proporcionado existe
				Optional<Coche> optionalCoche = cocheRepositorio.findById(idCoche);
				if (optionalCoche.isPresent()) {
					Coche cocheExistente = optionalCoche.get();

					// Verificar si el coche pertenece al usuario
					if (cocheExistente.getUsuario().getId().equals(usuario.getId())) {
						// Actualizar los campos del coche existente con los datos del nuevo coche
						cocheExistente.setMarca(nuevoCoche.getMarca());
						cocheExistente.setModelo(nuevoCoche.getModelo());
						cocheExistente.setImagenPrincipal(nuevoCoche.getImagenPrincipal());
						cocheExistente.setPrecio(nuevoCoche.getPrecio());
						cocheExistente.setAnyo(nuevoCoche.getAnyo());
						cocheExistente.setPotencia(nuevoCoche.getPotencia());
						cocheExistente.setKilometraje(nuevoCoche.getKilometraje());
						cocheExistente.setCombustible(nuevoCoche.getCombustible());
						cocheExistente.setConsumo(nuevoCoche.getConsumo());
						cocheExistente.setTipoCambio(nuevoCoche.getTipoCambio());
						cocheExistente.setCategoria(nuevoCoche.getCategoria());
						cocheExistente.setTipoVehiculo(nuevoCoche.getTipoVehiculo());
						cocheExistente.setTraccion(nuevoCoche.getTraccion());
						cocheExistente.setPlazas(nuevoCoche.getPlazas());
						cocheExistente.setPuertas(nuevoCoche.getPuertas());
						cocheExistente.setGarantia(nuevoCoche.getGarantia());
						cocheExistente.setPeso(nuevoCoche.getPeso());
						cocheExistente.setColor(nuevoCoche.getColor());
						cocheExistente.setNumeroMarchas(nuevoCoche.getNumeroMarchas());
						cocheExistente.setNumeroCilindros(nuevoCoche.getNumeroCilindros());
						cocheExistente.setCiudad(nuevoCoche.getCiudad());
						cocheExistente.setDescripcion(nuevoCoche.getDescripcion());
						cocheExistente.setTelefonoAdjunto(nuevoCoche.getTelefonoAdjunto());
						cocheExistente.setEmailAdjunto(nuevoCoche.getEmailAdjunto());

						// Guardar el coche actualizado
						cocheRepositorio.save(cocheExistente);

						return ResponseEntity.ok().body("Coche con ID " + idCoche + " actualizado correctamente");
					} else {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
								.body("No tienes permiso para actualizar este coche");
					}
				} else {
					return ResponseEntity.notFound().build();
				}
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado o no encontrado");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Debes iniciar sesión para actualizar un coche");
		}
	}

	public ResponseEntity<String> borrarCoche(Long cocheId) {
		try {
			// Verificar si el coche con el ID proporcionado existe
			Optional<Coche> optionalCoche = cocheRepositorio.findById(cocheId);
			if (!optionalCoche.isPresent()) {
				return new ResponseEntity<>("Coche no encontrado", HttpStatus.NOT_FOUND);
			}

			// Eliminar el coche
			cocheRepositorio.deleteById(cocheId);

			return new ResponseEntity<>("Coche eliminado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al eliminar el coche", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> borrarCochePropio(Long idCoche) {
		// Obtener el usuario actualmente autenticado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String username = authentication.getName();
			Usuario usuario = usuarioRepositorio.findByEmail(username).orElse(null);
			if (usuario != null) {
				// Verificar si el coche con el ID proporcionado existe
				Optional<Coche> optionalCoche = cocheRepositorio.findById(idCoche);
				if (optionalCoche.isPresent()) {
					Coche cocheExistente = optionalCoche.get();

					// Verificar si el coche pertenece al usuario
					if (cocheExistente.getUsuario().getId().equals(usuario.getId())) {
						// Eliminar el coche
						cocheRepositorio.deleteById(idCoche);

						return new ResponseEntity<>("Coche eliminado correctamente", HttpStatus.OK);
					} else {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
								.body("No tienes permiso para eliminar este coche");
					}
				} else {
					return ResponseEntity.notFound().build();
				}
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado o no encontrado");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Debes iniciar sesión para eliminar un coche");
		}
	}
}
