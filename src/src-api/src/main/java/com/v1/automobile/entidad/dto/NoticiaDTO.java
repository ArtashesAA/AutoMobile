package com.v1.automobile.entidad.dto;

import java.sql.Date;

public class NoticiaDTO {

	private Long id;
	private Date fecha;
	private String titulo;
	private String contenido;
	private String url_imagen;
	private String url_video;

	public NoticiaDTO() {

	}

	public NoticiaDTO(Long id, Date fecha, String titulo, String contenido, String url_imagen, String url_video) {
		this.id = id;
		this.fecha = fecha;
		this.titulo = titulo;
		this.contenido = contenido;
		this.url_imagen = url_imagen;
		this.url_video = url_video;
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

}
