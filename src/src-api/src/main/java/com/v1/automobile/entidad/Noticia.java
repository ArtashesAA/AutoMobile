package com.v1.automobile.entidad;

import java.sql.Timestamp;
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

@Entity
@Table(name = "noticia")
public class Noticia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "fecha", columnDefinition = "TIMESTAMP")
	private Timestamp fecha;

	@Column(name = "titulo", nullable = false)
	private String titulo;

	@Column(name = "contenido", nullable = false)
	private String contenido;

	@Column(name = "url_imagen")
	private String url_imagen;

	@Column(name = "url_video")
	private String url_video;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference(value = "usuario-noticias")
	private Usuario usuario;

	public Noticia() {

	}

	public Noticia(Long id, Timestamp fecha, String titulo, String contenido, String url_imagen, String url_video,
			Usuario usuario) {
		this.id = id;
		this.fecha = fecha;
		this.titulo = titulo;
		this.contenido = contenido;
		this.url_imagen = url_imagen;
		this.url_video = url_video;
		this.usuario = usuario;
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

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getUrl_imagen() {
		return url_imagen;
	}

	public void setUrl_imagen(String url_imagen) {
		this.url_imagen = url_imagen;
	}

	public String getUrl_video() {
		return url_video;
	}

	public void setUrl_video(String url_video) {
		this.url_video = url_video;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Noticia [getId()=" + getId() + ", getFecha()=" + getFecha() + ", getTitulo()=" + getTitulo()
				+ ", getContenido()=" + getContenido() + ", getUrl_imagen()=" + getUrl_imagen() + ", getUrl_video()="
				+ getUrl_video() + ", getUsuario()=" + getUsuario() + "]";
	}

}
