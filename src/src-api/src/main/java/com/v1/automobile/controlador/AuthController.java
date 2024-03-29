package com.v1.automobile.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v1.automobile.dto.ReqRes;
import com.v1.automobile.servicio.AuthService;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	/*
	 * Maneja la solicitud de registro
	 * 
	 * @param ReqRes que contiene la información del usuario que se va a registrar.
	 * 
	 * @return ResponseEntity con la respuesta de autenticación
	 */
	@PostMapping("/signup")
	public ResponseEntity<ResponseEntity<String>> signUp(@RequestBody ReqRes signUpRequest) {
		System.out.println("Registrado correctamente");
		return ResponseEntity.ok(authService.signUp(signUpRequest));
	}

	/*
	 * Maneja la solicitud de inicio de sesión
	 * 
	 * @param ReqRes que contiene la información del usuario que va a inciar sesion.
	 * 
	 * @return ResponseEntity con la respuesta del inicio de sesión
	 */
	@PostMapping("/signin")
	public ResponseEntity<ReqRes> signIn(@RequestBody ReqRes signInRequest) {
		return ResponseEntity.ok(authService.signIn(signInRequest));
	}

	/*
	 * Maneja la solicitud de refrescar página
	 * 
	 * @param ReqRes que contiene la información del token que se va a refrescar.
	 * 
	 * @return ResponseEntity con la respuesta.
	 */
	@PostMapping("/refresh")
	public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenRequest) {
		return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
	}
}
