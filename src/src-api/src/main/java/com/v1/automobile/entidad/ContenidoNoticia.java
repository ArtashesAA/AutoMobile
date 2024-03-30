package com.v1.automobile.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contenido_noticia")
public class ContenidoNoticia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "contenido", nullable = false)
	private String contenido;

	@Column(name = "imagen")
	private String imagen;

	@Column(name = "url_video")
	private String urlVideo;

	@ManyToOne
	@JoinColumn(name = "noticia_id", nullable = false)
	private Noticia noticia;

	public ContenidoNoticia() {

	}

	public ContenidoNoticia(Long id, String contenido, String imagen, String urlVideo, Noticia noticia) {
		this.id = id;
		this.contenido = contenido;
		this.imagen = imagen;
		this.urlVideo = urlVideo;
		this.noticia = noticia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getUrlVideo() {
		return urlVideo;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}

	@Override
	public String toString() {
		return "ContenidoNoticia [getId()=" + getId() + ", getContenido()=" + getContenido() + ", getImagen()="
				+ getImagen() + ", getUrlVideo()=" + getUrlVideo() + ", getNoticia()=" + getNoticia() + "]";
	}

}
