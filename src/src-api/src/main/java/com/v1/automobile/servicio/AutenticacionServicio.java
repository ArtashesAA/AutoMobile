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
			Usuario usuario = new Usuario();
			usuario.setNombre_usuario(registrationRequest.getNombre_usuario());
			usuario.setEmail(registrationRequest.getEmail());
			usuario.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

			// Por defecto se registra como usuario normal
			usuario.setRole("USER");
			Usuario usuarioResult = usuarioRepositorio.save(usuario);
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

	// Método para realizar el cierre de sesión
	public ResponseEntity<String> logout(String token) {
        try {
            // Extraer el nombre de usuario desde el token
            String userEmail = jwtUtils.extractUsername(token);

            // Verificar si el token es inválido (ya sea por expiración o por ser inválido en la lista)
            if (jwtUtils.isTokenInvalid(token)) {
                return ResponseEntity.badRequest().body("El token de sesión ya está invalidado");
            }

            // Si el token es válido y pertenece a un usuario existente
            if (jwtUtils.isTokenValid(token, usuarioRepositorio.findByEmail(userEmail).orElse(null))) {
                // Invalidar el token (agregarlo a la lista de tokens inválidos)
                jwtUtils.invalidateToken(token);

                // Podrías realizar otras operaciones de cierre de sesión aquí si es necesario

                return ResponseEntity.ok("Sesión cerrada correctamente");
            } else {
                return ResponseEntity.badRequest().body("Token inválido o no asociado a un usuario");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cerrar sesión: " + e.getMessage());
        }
    }
}
