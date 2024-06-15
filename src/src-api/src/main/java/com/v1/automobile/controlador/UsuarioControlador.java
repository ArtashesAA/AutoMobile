package com.v1.automobile.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v1.automobile.entidad.Usuario;
import com.v1.automobile.repositorio.UsuarioRepositorio;

@RestController
@RequestMapping("/api/v1")
public class UsuarioControlador {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * Recupera todos los usuarios. Puede acceder solo un admin
	 * 
	 * @return recupera todos los usuarios
	 */
	@GetMapping("/admin/usuario")
	public ResponseEntity<List<Usuario>> obtenerUsuarios() {
		List<Usuario> usuarios = usuarioRepositorio.findAll();

		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}

	/*
	 * Recupera un usuario por id. Puede acceder solo un admin
	 * 
	 * @Parameter id de usuario que se va a buscar
	 * 
	 * @return recupera el usuario por id
	 */
	@GetMapping("/admin/usuario/{id}")
	public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id) {
		Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
		if (usuario != null) {
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * Recupera el usuario que tiene la sesión iniciada. Puede acceder cualquier rol
	 * 
	 * @return recupera el usuario
	 */
	@GetMapping("/adminuser/usuario/actual")
	public ResponseEntity<Usuario> obtenerUsuarioActual() {
		// Obtener la autenticación actual del contexto de seguridad
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String username = authentication.getName();
			Usuario usuario = usuarioRepositorio.findByEmail(username).orElse(null);
			if (usuario != null) {
				// Crear un UsuarioActualDTO sin el campo password encriptado
				Usuario usuarioNuevo = new Usuario(usuario.getId(), usuario.getNombre_usuario(), usuario.getEmail(),
						usuario.getImagen_usuario(), usuario.getPassword(), usuario.getRole(), usuario.getCoches(),
						usuario.getNoticias());
				usuario.setPassword(usuario.getPassword());
				return new ResponseEntity<>(usuarioNuevo, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Usuario no autenticado o no encontrado
	}

	/*
	 * Añade un usuario a la bbdd. Puede acceder sin rol
	 * 
	 * @Parameter usuario que se va a crear
	 * 
	 * @return recupera el usuario guardado
	 */
	@PostMapping("/public/usuario")
	public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioCreado = new Usuario(usuario.getId(), usuario.getNombre_usuario(), usuario.getEmail(),
				passwordEncoder.encode(usuario.getPassword()), usuario.getImagen_usuario(), "USER");

		usuarioRepositorio.save(usuarioCreado);
		return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
	}

	/*
	 * Actualiza un usuario de la bbdd. Puede acceder solo el admin
	 * 
	 * @Parameter id del usuario que se quiere actualizar
	 * 
	 * @Parameter usuarioActualizado que contiene los datos del usuario nuevo que va
	 * a sustituir al otro
	 * 
	 * @return Recupera que se ha actualizado el usuario con su id
	 */
	@PutMapping("/admin/usuario/{id}")
	public ResponseEntity<String> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioActualizado) {
		Usuario usuarioExistente = usuarioRepositorio.findById(id).orElse(null);
		if (usuarioExistente != null) {
			usuarioExistente.setNombre_usuario(usuarioActualizado.getNombre_usuario());
			usuarioExistente.setEmail(usuarioActualizado.getEmail());
			usuarioExistente.setImagen_usuario(usuarioActualizado.getImagen_usuario());

			if (!usuarioActualizado.getPassword().isEmpty()) {
				usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
			}

			usuarioRepositorio.save(usuarioExistente);
			return ResponseEntity.ok().body("Usuario con ID " + id + " actualizado correctamente");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * Actualiza el perfil del usuario autenticado. Solo puede acceder el usuario
	 * autenticado.
	 * 
	 * @Parameter usuarioActualizado que contiene los datos del perfil actualizado
	 * 
	 * @return Recupera un mensaje indicando si se ha actualizado el perfil
	 * correctamente o si ha ocurrido algún error.
	 */
	@PutMapping("/adminuser/usuario")
	public ResponseEntity<String> actualizarPerfil(@RequestBody Usuario usuarioActualizado) {
		// Obtener la autenticación actual del contexto de seguridad
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String username = authentication.getName();
			Usuario usuarioAutenticado = usuarioRepositorio.findByEmail(username).orElse(null);
			if (usuarioAutenticado != null) {
				usuarioAutenticado.setNombre_usuario(usuarioActualizado.getNombre_usuario());
				usuarioAutenticado.setEmail(usuarioActualizado.getEmail());
				usuarioAutenticado.setImagen_usuario(usuarioActualizado.getImagen_usuario());

				// Verificar si se proporcionó una nueva contraseña
				if (!usuarioActualizado.getPassword().isEmpty()) {
					usuarioAutenticado.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
				} else {
					// Si no se proporciona una nueva contraseña, mantener la misma
					usuarioActualizado.setPassword(usuarioAutenticado.getPassword());
				}

				usuarioRepositorio.save(usuarioAutenticado);
				return ResponseEntity.ok().body("Perfil actualizado correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("No se pudo encontrar el usuario autenticado");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Debes iniciar sesión para actualizar tu perfil");
		}
	}

	/*
	 * Borra un coche de la bbdd. Puede acceder solo el admin
	 * 
	 * @Parameter id del coche que se quiere borrar
	 */
	@DeleteMapping("/admin/usuario/{id}")
	public ResponseEntity<String> eliminarUsuario(@PathVariable Integer id) {
		usuarioRepositorio.deleteById(id);
		return new ResponseEntity<>("Usuario con id " + id + " eliminado correctamente", HttpStatus.OK);
	}

}
