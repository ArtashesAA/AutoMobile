package com.v1.automobile.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.v1.automobile.dto.ReqRes;
import com.v1.automobile.entidad.Usuario;
import com.v1.automobile.repositorio.UsuarioRepositorio;

import java.util.HashMap;

@Service
public class AuthService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;

	public ReqRes signUp(ReqRes registrationRequest) {
		ReqRes resp = new ReqRes();
		try {
			Usuario usuarios = new Usuario();
			usuarios.setNombre_usuario(registrationRequest.getName());
			usuarios.setEmail(registrationRequest.getEmail());
			usuarios.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

			// Por defecto se registra como usuario normal
			usuarios.setRole("USER");
			Usuario usuarioResult = usuarioRepositorio.save(usuarios);
			if (usuarioResult != null && usuarioResult.getId() > 0) {
				resp.setUsuarios(usuarioResult);
				resp.setMessage("User Saved Successfully");
				resp.setStatusCode(200);
			}
		} catch (Exception e) {
			resp.setStatusCode(500);
			resp.setError(e.getMessage());
		}
		return resp;
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

	public ReqRes refreshToken(ReqRes refreshTokenReqiest) {
		ReqRes response = new ReqRes();
		String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
		Usuario users = usuarioRepositorio.findByEmail(ourEmail).orElseThrow();
		if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
			var jwt = jwtUtils.generateToken(users);
			response.setStatusCode(200);
			response.setToken(jwt);
			response.setRefreshToken(refreshTokenReqiest.getToken());
			response.setExpirationTime("24Hr");
			response.setMessage("Successfully Refreshed Token");
		}
		response.setStatusCode(500);
		return response;
	}
}
