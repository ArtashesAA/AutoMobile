package com.v1.automobile.entidad.dto;

import java.sql.Date;

import com.v1.automobile.entidad.Coche;
import com.v1.automobile.entidad.Usuario;

public class FavoritoDTO {

	private Long id;

	private Date fecha;

	private Usuario usuario_id;

	private Coche coche_id;

	public FavoritoDTO() {

	}

	public FavoritoDTO(Long id, Date fecha, Usuario usuario_id, Coche coche_id) {
		this.id = id;
		this.fecha = fecha;
		this.usuario_id = usuario_id;
		this.coche_id = coche_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Usuario usuario_id) {
		this.usuario_id = usuario_id;
	}

	public Coche getCoche_id() {
		return coche_id;
	}

	public void setCoche_id(Coche coche_id) {
		this.coche_id = coche_id;
	}

}
