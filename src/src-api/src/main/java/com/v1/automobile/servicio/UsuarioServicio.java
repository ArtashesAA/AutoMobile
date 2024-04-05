package com.v1.automobile.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.v1.automobile.entidad.Usuario;
import com.v1.automobile.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepositorio.findByEmail(username).orElseThrow();
	}

	public Usuario guardarUsuario(Usuario usuario) {
		// Cifrar la contraseña antes de guardarla
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioRepositorio.save(usuario);
	}

	public List<Usuario> obtenerUsuarios() {
		return usuarioRepositorio.findAll();
	}

	public Usuario obtenerUsuarioPorId(Integer id) {
		return usuarioRepositorio.findById(id).orElse(null);
	}

	public Usuario actualizarUsuario(Integer id, Usuario usuarioActualizado) {
		Usuario usuarioExistente = usuarioRepositorio.findById(id).orElse(null);
		if (usuarioExistente != null) {
			usuarioExistente.setNombre_usuario(usuarioActualizado.getNombre_usuario());
			usuarioExistente.setEmail(usuarioActualizado.getEmail());
			usuarioExistente.setImagen_usuario(usuarioActualizado.getImagen_usuario());

			if (!usuarioActualizado.getPassword().isEmpty()) {
				usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
			}
			usuarioExistente.setRole(usuarioActualizado.getRole());
			return usuarioRepositorio.save(usuarioExistente);
		}
		return null;
	}

	public boolean borrarUsuario(Integer id) {
		if (usuarioRepositorio.existsById(id)) {
			usuarioRepositorio.deleteById(id);
			return true;
		}
		return false;
	}

	public boolean existe(Integer id) {
		return usuarioRepositorio.existsById(id);
	}

}
