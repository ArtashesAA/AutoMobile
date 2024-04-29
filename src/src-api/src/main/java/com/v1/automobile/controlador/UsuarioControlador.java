package com.v1.automobile.controlador;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.v1.automobile.entidad.dto.UsuarioActualDTO;
import com.v1.automobile.entidad.dto.UsuarioCreacion;
import com.v1.automobile.entidad.dto.UsuarioDTO;
import com.v1.automobile.repositorio.UsuarioRepositorio;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioControlador {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public ResponseEntity<List<UsuarioActualDTO>> obtenerTodosUsuarios() {
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		List<UsuarioActualDTO> usuariosDTO = usuarios.stream().map(usuario -> {
			UsuarioActualDTO dto = new UsuarioActualDTO();
			dto.setId(usuario.getId());
			dto.setNombre_usuario(usuario.getNombre_usuario());
			dto.setEmail(usuario.getEmail());
			dto.setImagen_usuario(usuario.getImagen_usuario());
			dto.setPassword(usuario.getPassword());
			dto.setRole(usuario.getRole());
			return dto;
		}).collect(Collectors.toList());

		return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioActualDTO> obtenerUsuarioPorId(@PathVariable Integer id) {
		Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
		if (usuario != null) {
			// Crear un UsuarioActualDTO con los datos requeridos
			UsuarioActualDTO usuarioDTO = new UsuarioActualDTO(usuario.getId(), usuario.getNombre_usuario(),
					usuario.getEmail(), usuario.getImagen_usuario(), usuario.getPassword(), usuario.getRole());
			return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/actual")
	public ResponseEntity<UsuarioActualDTO> obtenerUsuarioActual() {
		// Obtener la autenticación actual del contexto de seguridad
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String username = authentication.getName();
			Usuario usuario = usuarioRepositorio.findByEmail(username).orElse(null);
			if (usuario != null) {
				// Crear un UsuarioActualDTO con los datos requeridos
				UsuarioActualDTO usuarioDTO = new UsuarioActualDTO(usuario.getId(), usuario.getNombre_usuario(),
						usuario.getEmail(), usuario.getImagen_usuario(), usuario.getPassword(), usuario.getRole());
				return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Usuario no autenticado o no encontrado
	}

	@PostMapping
	public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioCreacion usuario) {
		Usuario usuarioCreado = new Usuario();
		usuarioCreado.setId(usuario.getId());
		usuarioCreado.setNombre_usuario(usuario.getNombre_usuario());
		usuarioCreado.setEmail(usuario.getEmail());
		usuarioCreado.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioCreado.setImagen_usuario(usuario.getImagen_usuario());
		usuarioCreado.setRole("USER");
		usuarioRepositorio.save(usuarioCreado);
		return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id,
			@RequestBody Usuario usuarioActualizado) {
		Usuario usuarioExistente = usuarioRepositorio.findById(id).orElse(null);
		if (usuarioExistente != null) {
			usuarioExistente.setNombre_usuario(usuarioActualizado.getNombre_usuario());
			usuarioExistente.setEmail(usuarioActualizado.getEmail());
			usuarioExistente.setImagen_usuario(usuarioActualizado.getImagen_usuario());

			if (!usuarioActualizado.getPassword().isEmpty()) {
				usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
			}

			usuarioRepositorio.save(usuarioExistente);
			return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
		if (!usuarioRepositorio.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		usuarioRepositorio.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
