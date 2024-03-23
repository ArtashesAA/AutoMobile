package com.v1.automobile.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "coche_id", nullable = false)
	private Coche coche;

	@NotNull(message = "La imagen no puede estar vacío")
	@Column(name = "imagen_url", nullable = false)
	private String imageUrl;

	public Imagen() {

	}

	public Imagen(Long id, Coche coche, @NotNull(message = "La imagen no puede estar vacío") String imageUrl) {
		this.id = id;
		this.coche = coche;
		this.imageUrl = imageUrl;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Imagen [getId()=" + getId() + ", getCoche()=" + getCoche() + ", getImageUrl()=" + getImageUrl() + "]";
	}

}