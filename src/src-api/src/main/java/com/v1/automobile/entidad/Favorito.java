package com.v1.automobile.entidad;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "favorito")
public class Favorito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull(message = "La fecha no puede estar nula")
	@Column(name = "fecha", nullable = false, columnDefinition = "TIMESTAMP")
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference("usuario-favoritos")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coche_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference("coche-favoritos")
	private Coche coche;

	public Favorito() {

	}

	public Favorito(Long id, Usuario usuario, Coche coche,
			@NotNull(message = "La fecha no puede estar nula") Date fecha) {
		this.id = id;
		this.usuario = usuario;
		this.coche = coche;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Favorito [getId()=" + getId() + ", getUsuario()=" + getUsuario() + ", getCoche()=" + getCoche()
				+ ", getFecha()=" + getFecha() + "]";
	}

}
