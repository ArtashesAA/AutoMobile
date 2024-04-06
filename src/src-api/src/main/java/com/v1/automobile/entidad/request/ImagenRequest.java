package com.v1.automobile.entidad.request;

public class ImagenRequest {
	private Long coche_id;
	private String imagen_url;

	public ImagenRequest() {

	}

	public ImagenRequest(Long coche_id, String imagen_url) {
		this.coche_id = coche_id;
		this.imagen_url = imagen_url;
	}

	public Long getCoche_id() {
		return coche_id;
	}

	public void setCoche_id(Long coche_id) {
		this.coche_id = coche_id;
	}

	public String getImagen_url() {
		return imagen_url;
	}

	public void setImagen_url(String imagen_url) {
		this.imagen_url = imagen_url;
	}

}
