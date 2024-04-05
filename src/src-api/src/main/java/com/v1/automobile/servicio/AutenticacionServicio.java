package com.v1.automobile.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.v1.automobile.dto.ReqRes;
import com.v1.automobile.entidad.Usuario;
import com.v1.automobile.repositorio.UsuarioRepositorio;

import java.util.HashMap;

@Service
public class AutenticacionServicio {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;

	public ResponseEntity<String> signUp(ReqRes registrationRequest) {
		try {
			Usuario usuarios = new Usuario();
			usuarios.setNombre_usuario(registrationRequest.getNombre_usuario());
			usuarios.setEmail(registrationRequest.getEmail());
			usuarios.setImagen_usuario(registrationRequest.getImagen_usuario());
			usuarios.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

			// Por defecto se registra como usuario normal
			usuarios.setRole("USER");
			Usuario usuarioResult = usuarioRepositorio.save(usuarios);
			if (usuarioResult != null && usuarioResult.getId() > 0) {
				String mensaje = "Usuario " + usuarioResult.getNombre_usuario() + " ha sido registrado correctamente";
				return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			String mensajeError = "Error al registrar usuario: " + e.getMessage();
			return new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String mensajeError = "Error al registrar usuario";
		return new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ReqRes signIn(ReqRes signinRequest) {
		ReqRes response = new ReqRes();

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
			var user = usuarioRepositorio.findByEmail(signinRequest.getEmail()).orElseThrow();
			System.out.println("USER IS: " + user);
			var jwt = jwtUtils.generateToken(user);
			var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
			response.setStatusCode(200);
			response.setToken(jwt);
			response.setRefreshToken(refreshToken);
			response.setExpirationTime("24Hr");
			response.setMessage("Successfully Signed In");
		} catch (Exception e) {
			response.setStatusCode(500);
			response.setError(e.getMessage());
		}
		return response;
	}

	public ReqRes refreshToken(ReqRes refreshTokenRequest) {
	    ReqRes response = new ReqRes();
	    try {
	        String userEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
	        Usuario user = usuarioRepositorio.findByEmail(userEmail).orElseThrow();

	        if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)) {
	            String newToken = jwtUtils.generateToken(user);
	            response.setStatusCode(200);
	            response.setToken(newToken);
	            response.setRefreshToken(refreshTokenRequest.getToken());
	            response.setExpirationTime("24Hr");
	            response.setMessage("Successfully Refreshed Token");
	        } else {
	            response.setStatusCode(500);
	            response.setMessage("Token is not valid");
	        }
	    } catch (Exception e) {
	        response.setStatusCode(500);
	        response.setMessage("Error refreshing token: " + e.getMessage());
	    }
	    return response;
	}

}
