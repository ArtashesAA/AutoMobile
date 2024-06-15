package com.v1.automobile.entidad;

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
@Table(name = "imagen")
public class Imagen {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@NotNull(message = "La imagen no puede estar vacío")
	@Column(name = "imagen_url", nullable = false)
	private String imagen_url;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coche_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference("coche-imagenes")
	private Coche coche;

	public Imagen() {

	}

	public Imagen(Long id, @NotNull(message = "La imagen no puede estar vacío") String imagen_url, Coche coche) {
		this.id = id;
		this.imagen_url = imagen_url;
		this.coche = coche;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	public String getImagen_url() {
		return imagen_url;
	}

	public void setImagen_url(String imagen_url) {
		this.imagen_url = imagen_url;
	}

	@Override
	public String toString() {
		return "Imagen [getId()=" + getId() + ", getCoche()=" + getCoche() + ", getImagen_url()=" + getImagen_url()
				+ "]";
	}

}