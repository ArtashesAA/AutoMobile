package com.v1.automobile.entidad;

public class UsuarioDTO {

	private Integer id;
	private String nombre_usuario;

	public UsuarioDTO() {

	}

	public UsuarioDTO(Integer id, String nombre_usuario) {
		this.id = id;
		this.nombre_usuario = nombre_usuario;
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

	@Override
	public String toString() {
		return "UsuarioDTO [getId()=" + getId() + ", getNombre_usuario()=" + getNombre_usuario() + "]";
	}

}
