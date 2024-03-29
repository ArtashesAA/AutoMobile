package com.v1.automobile.entidad;

public class ImagenDTO {

	private String imagen_url;

	public ImagenDTO() {

	}

	public ImagenDTO(String imagen_url) {
		this.imagen_url = imagen_url;
	}

	public String getImagen_url() {
		return imagen_url;
	}

	public void setImagen_url(String imagen_url) {
		this.imagen_url = imagen_url;
	}

	@Override
	public String toString() {
		return "ImagenDTO [getImagen_url()=" + getImagen_url() + "]";
	}

}
