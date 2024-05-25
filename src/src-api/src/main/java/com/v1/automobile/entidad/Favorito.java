package com.v1.automobile.entidad;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "favorito")
public class Favorito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "fecha", columnDefinition = "TIMESTAMP")
	private Timestamp fecha;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference("usuario-favoritos")
	private Usuario usuario;

	private Long coche_id_recuperado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coche_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference("coche-favoritos")
	private Coche coche;

	public Favorito() {

	}

	public Favorito(Long id, Usuario usuario, Coche coche, Timestamp fecha) {
		this.id = id;
		this.usuario = usuario;
		this.coche = coche;
		this.fecha = fecha;
	}

	@PrePersist
	public void prePersist() {
		if (this.fecha == null) {
			this.fecha = Timestamp.valueOf(LocalDateTime.now());
		}
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

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Long getCoche_id_recuperado() {
		return coche.getId();
	}

	public void setCoche_id_recuperado(Long coche_id_recuperado) {
		this.coche_id_recuperado = coche.getId();
	}

	@Override
	public String toString() {
		return "Favorito [getId()=" + getId() + ", getUsuario()=" + getUsuario() + ", getCoche()=" + getCoche()
				+ ", getFecha()=" + getFecha() + "]";
	}

}
