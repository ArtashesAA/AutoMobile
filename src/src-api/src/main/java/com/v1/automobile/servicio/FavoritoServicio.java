package com.v1.automobile.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.v1.automobile.entidad.Favorito;
import com.v1.automobile.entidad.Usuario;
import com.v1.automobile.repositorio.FavoritoRepositorio;
import com.v1.automobile.repositorio.UsuarioRepositorio;

@Service
public class FavoritoServicio {

	@Autowired
	private FavoritoRepositorio favoritoRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	public Optional<Favorito> obtenerFavoritoPorId(Long id) {
		return favoritoRepositorio.findById(id);
	}

	public List<Favorito> obtenerFavoritos() {
		return favoritoRepositorio.findAll();
	}

	public List<Favorito> obtenerFavoritosPorIdUsuario(Long idUsuario) {
		return favoritoRepositorio.findByUsuarioId(idUsuario);

	}

	public Favorito crearFavorito(Favorito favorito) {
		return favoritoRepositorio.save(favorito);
	}

	public ResponseEntity<String> borrarFavoritoPorId(Long id) {
		try {
			favoritoRepositorio.deleteById(id);
			return new ResponseEntity<>("Favorito eliminado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al eliminar el favorito", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<String> borrarFavoritoPropioPorId(Long id) {
		// Obtener el usuario actualmente autenticado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String username = authentication.getName();
			Usuario usuario = usuarioRepositorio.findByEmail(username).orElse(null);
			if (usuario != null) {
				// Verificar si el favorito con el ID proporcionado existe
				Optional<Favorito> optionalFavorito = favoritoRepositorio.findById(id);
				if (optionalFavorito.isPresent()) {
					Favorito favoritoExistente = optionalFavorito.get();

					// Verificar si el favorito pertenece al usuario
					if (favoritoExistente.getUsuario().getId().equals(usuario.getId())) {
						// Eliminar el favorito
						favoritoRepositorio.deleteById(id);

						return new ResponseEntity<>("Favorito eliminado correctamente", HttpStatus.OK);
					} else {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
								.body("No tienes permiso para eliminar este favorito");
					}
				} else {
					return ResponseEntity.notFound().build();
				}
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado o no encontrado");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Debes iniciar sesión para eliminar un favorito");
		}
	}

}
