package com.v1.automobile.entidad;

import java.util.ArrayList;
import java.util.List;

public class CocheDTO {

	private String marca;

	private String modelo;

	private Integer anyo;

	private Integer potencia;

	private Integer kilometraje;

	private Integer peso;

	private String combustible;

	private String color;

	private Integer precio;

	private String descripcion;

	private UsuarioDTO usuario;

	private List<ImagenDTO> imagenes = new ArrayList<>();

	public CocheDTO() {

	}

	public CocheDTO(String marca, String modelo, Integer anyo, Integer potencia, Integer kilometraje, Integer peso,
			String combustible, String color, Integer precio, String descripcion, UsuarioDTO usuario,
			List<ImagenDTO> imagenes) {

		this.marca = marca;
		this.modelo = modelo;
		this.anyo = anyo;
		this.potencia = potencia;
		this.kilometraje = kilometraje;
		this.peso = peso;
		this.combustible = combustible;
		this.color = color;
		this.precio = precio;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.imagenes = imagenes;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAnyo() {
		return anyo;
	}

	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}

	public Integer getPotencia() {
		return potencia;
	}

	public void setPotencia(Integer potencia) {
		this.potencia = potencia;
	}

	public Integer getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(Integer kilometraje) {
		this.kilometraje = kilometraje;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public String getCombustible() {
		return combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<ImagenDTO> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<ImagenDTO> imagenes) {
		this.imagenes = imagenes;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "CocheDTO [getMarca()=" + getMarca() + ", getModelo()=" + getModelo() + ", getAnyo()=" + getAnyo()
				+ ", getPotencia()=" + getPotencia() + ", getKilometraje()=" + getKilometraje() + ", getPeso()="
				+ getPeso() + ", getCombustible()=" + getCombustible() + ", getColor()=" + getColor() + ", getPrecio()="
				+ getPrecio() + ", getDescripcion()=" + getDescripcion() + ", getImagenes()=" + getImagenes()
				+ ", getUsuario()=" + getUsuario() + "]";
	}

}
