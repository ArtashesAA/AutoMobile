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
public class ImagenServicio {
	@Autowired
	private CocheRepositorio cocheRepositorio;

	@Autowired
	private ImagenRepositorio imagenRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	// Recupera todas las imagenes
	public List<Imagen> obtenerImagenes() {
		return imagenRepositorio.findAll();
	}

	// Recupera una imagen por su id
	public ResponseEntity<Optional<Imagen>> obtenerImagenPorId(Long id) {
		Optional<Imagen> imagenOptional = imagenRepositorio.findById(id);
		if (imagenOptional.isPresent()) {
			return ResponseEntity.ok(imagenOptional);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Recupera las imagenes por el id del coche
	public ResponseEntity<List<Imagen>> obtenerImagenesPorCocheId(Long cocheId) {
		List<Imagen> imagenes = imagenRepositorio.findByCocheId(cocheId);
		if (!imagenes.isEmpty()) {
			return new ResponseEntity<>(imagenes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Crea una imagen
	public ResponseEntity<String> crearImagen(Long coche_id, String imagen_url) {
		// Verificar si el coche con el ID proporcionado existe
		Coche coche = cocheRepositorio.findById(coche_id).orElse(null);
		if (coche == null) {
			return new ResponseEntity<>("Coche no encontrado", HttpStatus.NOT_FOUND);
		}

		// Crear una nueva instancia de Imagen y asociarla al coche
		Imagen imagen = new Imagen();
		imagen.setCoche(coche);
		imagen.setImagen_url(imagen_url);

		// Guardar la imagen en la base de datos
		imagen = imagenRepositorio.save(imagen);

		// Obtener el ID de la imagen creada
		Long imagenId = imagen.getId();

		// Respuesta indicando que la imagen se ha creado correctamente
		return ResponseEntity.ok().body("Imagen con ID " + imagenId + " creado correctamente");
	}

	// Actualiza una imagen
	public ResponseEntity<String> actualizarImagen(Long imagenId, Imagen nuevaImagen) {
		Optional<Imagen> optionalImagen = imagenRepositorio.findById(imagenId);

		if (optionalImagen.isPresent()) {
			Imagen imagenExistente = optionalImagen.get();
			// Actualizar solo los campos que sean modificables
			if (nuevaImagen.getImagen_url() != null) {
				imagenExistente.setImagen_url(nuevaImagen.getImagen_url());
			}

			imagenRepositorio.save(imagenExistente);
			return ResponseEntity.ok().body("Imagen con ID " + imagenId + " actualizado correctamente");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Actualiza una imagen
	public ResponseEntity<String> actualizarImagenPropio(Long imagenId, Imagen nuevaImagen) {
		// Obtener el usuario actualmente autenticado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String username = authentication.getName();
			Usuario usuario = usuarioRepositorio.findByEmail(username).orElse(null);
			if (usuario != null) {
				// Verificar si la imagen con el ID proporcionado existe
				Optional<Imagen> optionalImagen = imagenRepositorio.findById(imagenId);
				if (optionalImagen.isPresent()) {
					Imagen imagenExistente = optionalImagen.get();

					// Verificar si la imagen pertenece al usuario
					if (imagenExistente.getCoche().getUsuario().getId().equals(usuario.getId())) {
						// Actualizar solo los campos que sean modificables
						if (nuevaImagen.getImagen_url() != null) {
							imagenExistente.setImagen_url(nuevaImagen.getImagen_url());
						}

						imagenRepositorio.save(imagenExistente);
						return ResponseEntity.ok().body("Imagen con ID " + imagenId + " actualizado correctamente");
					} else {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
								.body("No tienes permiso para actualizar esta imagen");
					}
				} else {
					return ResponseEntity.notFound().build();
				}
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado o no encontrado");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Debes iniciar sesión para actualizar una imagen");
		}
	}

	public ResponseEntity<String> borrarImagen(Long id) {
		try {
			imagenRepositorio.deleteById(id);
			return new ResponseEntity<>("Imagen con ID " + id + " borrada correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al borrar la imagen con ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> borrarImagenPropio(Long id) {
		// Obtener el usuario actualmente autenticado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String username = authentication.getName();
			Usuario usuario = usuarioRepositorio.findByEmail(username).orElse(null);
			if (usuario != null) {
				// Verificar si la imagen con el ID proporcionado existe
				Optional<Imagen> optionalImagen = imagenRepositorio.findById(id);
				if (optionalImagen.isPresent()) {
					Imagen imagenExistente = optionalImagen.get();

					// Verificar si la imagen pertenece al usuario
					if (imagenExistente.getCoche().getUsuario().getId().equals(usuario.getId())) {
						// Eliminar la imagen
						imagenRepositorio.deleteById(id);

						return new ResponseEntity<>("Imagen con ID " + id + " borrada correctamente", HttpStatus.OK);
					} else {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
								.body("No tienes permiso para eliminar esta imagen");
					}
				} else {
					return ResponseEntity.notFound().build();
				}
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado o no encontrado");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Debes iniciar sesión para eliminar una imagen");
		}
	}
}
