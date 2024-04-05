package com.v1.automobile.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v1.automobile.entidad.Usuario;
import com.v1.automobile.servicio.UsuarioServicio;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioControlador {

	@Autowired
	private UsuarioServicio usuarioServicio;

	@GetMapping
	public ResponseEntity<List<Usuario>> obtenerTodosUsuarios() {
		List<Usuario> usuarios = usuarioServicio.obtenerUsuarios();
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id) {
		Usuario usuario = usuarioServicio.obtenerUsuarioPorId(id);
		if (usuario != null) {
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioCreado = usuarioServicio.guardarUsuario(usuario);
		return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id,
			@RequestBody Usuario usuarioActualizado) {
		usuarioServicio.actualizarUsuario(id, usuarioActualizado);
		if (usuarioActualizado != null) {
			return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
		if (!usuarioServicio.existe(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		usuarioServicio.borrarUsuario(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
