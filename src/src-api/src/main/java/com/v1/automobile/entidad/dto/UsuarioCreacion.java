package com.v1.automobile.entidad.dto;

public class UsuarioCreacion {
	private Integer id;
	private String nombre_usuario;
	private String email;
	private String imagen_usuario;
	private String password;

	public UsuarioCreacion() {

	}

	public UsuarioCreacion(Integer id, String nombre_usuario, String email, String imagen_usuario, String password) {
		this.id = id;
		this.nombre_usuario = nombre_usuario;
		this.email = email;
		this.imagen_usuario = imagen_usuario;
		this.password = password;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImagen_usuario() {
		return imagen_usuario;
	}

	public void setImagen_usuario(String imagen_usuario) {
		this.imagen_usuario = imagen_usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UsuarioCreacion [getId()=" + getId() + ", getNombre_usuario()=" + getNombre_usuario() + ", getEmail()="
				+ getEmail() + ", getImagen_usuario()=" + getImagen_usuario() + ", getPassword()=" + getPassword()
				+ "]";
	}

}
