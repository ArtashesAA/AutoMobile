package com.v1.automobile.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v1.automobile.entidad.Favorito;
import com.v1.automobile.servicio.FavoritoServicio;

@RestController
@RequestMapping("/api/v1")
public class FavoritoControlador {

	@Autowired
	private FavoritoServicio favoritoServicio;

	/*
	 * Recupera todos los favoritos. Puede acceder un admin
	 * 
	 * @return recupera todos los favoritos
	 */
	@GetMapping("/admin/favorito")
	public ResponseEntity<List<Favorito>> obtenerFavoritos() {
		List<Favorito> favoritos = (List<Favorito>) favoritoServicio.obtenerFavoritos();
		return ResponseEntity.ok().body(favoritos);
	}

	/*
	 * Recupera un favorito por id. Puede acceder un admin
	 * 
	 * @Parameter id de favorito que se va a buscar
	 * 
	 * @return recupera el Favorito
	 */
	@GetMapping("/admin/favorito/{id}")
	public ResponseEntity<Optional<Favorito>> obtenerFavoritoPorId(@PathVariable Long id) {
		Optional<Favorito> favorito = favoritoServicio.obtenerFavoritoPorId(id);
		if (favorito != null) {
			return ResponseEntity.ok().body(favorito);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * Recupera los favoritos por id del usuario. Puede acceder admin
	 * 
	 * @Parameter id de usuario del cual se quiere recuperar los favoritos
	 * 
	 * @return recupera los favoritos por id del usuario
	 */
	@GetMapping("/adminuser/favorito/usuario/{idUsuario}")
    public ResponseEntity<List<Favorito>> obtenerFavoritosPorIdUsuario(@PathVariable Long idUsuario) {
        List<Favorito> favoritos = favoritoServicio.obtenerFavoritosPorIdUsuario(idUsuario);
        return ResponseEntity.ok(favoritos);
    }

	/*
	 * Añade un favorito a la bbdd. Puede acceder admin o usuario
	 * 
	 * @Parameter Favorito que se va a crear
	 * 
	 * @return recupera el favorito guardado
	 */
	@PostMapping("/adminuser/favorito")
	public ResponseEntity<Favorito> crearFavorito(@RequestBody Favorito favorito) {
		Favorito nuevaFavorito = favoritoServicio.crearFavorito(favorito);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFavorito);
	}

	/*
	 * Borra un favorito de la bbdd. Puede acceder admin
	 * 
	 * @Parameter id de la noticia que se quiere borrar
	 */
	@DeleteMapping("/admin/favorito/{id}")
	public ResponseEntity<String> borrarFavoritoPorId(@PathVariable Long id) {
		return favoritoServicio.borrarFavoritoPorId(id);
	}
	
	/*
	 * Borra un favorito de la bbdd. Puede acceder admin
	 * 
	 * @Parameter id de la noticia que se quiere borrar
	 */
	@DeleteMapping("/adminuser/favorito/{id}")
	public ResponseEntity<String> borrarFavoritoPropioPorId(@PathVariable Long id) {
		return favoritoServicio.borrarFavoritoPropioPorId(id);
	}
}
