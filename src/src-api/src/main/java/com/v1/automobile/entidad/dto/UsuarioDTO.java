package com.v1.automobile.entidad.dto;

import java.util.ArrayList;
import java.util.List;

import com.v1.automobile.entidad.Coche;
import com.v1.automobile.entidad.Noticia;

public class UsuarioDTO {

	private Integer id;
	private String nombre_usuario;
	private String imagen_usuario;
	private String role;
	private List<Coche> coches = new ArrayList<>();
	private List<Noticia> noticias = new ArrayList<>();

	public UsuarioDTO() {

	}

	public UsuarioDTO(Integer id, String nombre_usuario, String imagen_usuario, String role, List<Coche> coches,
			List<Noticia> noticias) {
		this.id = id;
		this.nombre_usuario = nombre_usuario;
		this.imagen_usuario = imagen_usuario;
		this.role = role;
		this.coches = coches;
		this.noticias = noticias;
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

	public String getImagen_usuario() {
		return imagen_usuario;
	}

	public void setImagen_usuario(String imagen_usuario) {
		this.imagen_usuario = imagen_usuario;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Coche> getCoches() {
		return coches;
	}

	public void setCoches(List<Coche> coches) {
		this.coches = coches;
	}

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}

}
