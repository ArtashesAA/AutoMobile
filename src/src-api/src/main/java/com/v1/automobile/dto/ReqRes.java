package com.v1.automobile.dto;

import com.v1.automobile.entidad.Coche;
import com.v1.automobile.entidad.Usuario;

import lombok.Data;

import java.util.List;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Data
@EnableWebSecurity
public class ReqRes {

	private int statusCode;
	private String error;
	private String message;
	private String token;
	private String refreshToken;
	private String expirationTime;
	private String nombre_usuario;
	private String imagen_usuario;
	private String email;
	private String role;
	private String password;
	private List<Coche> coches;
	private Usuario usuarios;

	public ReqRes() {

	}

	public ReqRes(String nombre_usuario, String email, String password) {
		this.nombre_usuario = nombre_usuario;
		this.email = email;
		this.password = password;
	}

	public ReqRes(String nombre_usuario, String password, String message, String token) {
		this.nombre_usuario = nombre_usuario;
		this.password = password;
		this.message = message;
		this.token = token;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getImagen_usuario() {
		return imagen_usuario;
	}

	public void setImagen_usuario(String imagen_usuario) {
		this.imagen_usuario = imagen_usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Coche> getCoches() {
		return coches;
	}

	public void setCoches(List<Coche> coches) {
		this.coches = coches;
	}

	public Usuario getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuario usuarios) {
		this.usuarios = usuarios;
	}

}
