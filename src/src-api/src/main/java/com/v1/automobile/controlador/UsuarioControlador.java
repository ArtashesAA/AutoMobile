package com.v1.automobile.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v1/usuario")
public class UsuarioControlador {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public ResponseEntity<List<Usuario>> obtenerTodosUsuarios() {
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id) {
		Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
		if (usuario != null) {
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioCreado = usuario;
		usuarioCreado.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioRepositorio.save(usuarioCreado);
		return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
	}

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
			usuarioExistente.setRole(usuarioActualizado.getRole());
			usuarioRepositorio.save(usuarioExistente);
			return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
		if (!usuarioRepositorio.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		usuarioRepositorio.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
