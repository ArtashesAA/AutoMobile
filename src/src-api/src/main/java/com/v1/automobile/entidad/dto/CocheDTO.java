package com.v1.automobile.entidad.dto;

public class CocheDTO {
	private Long id;
	private String marca;
	private String modelo;
	private String imagen_principal;
	private Integer precio;
	private Integer anyo;
	private Integer potencia;
	private Integer kilometraje;
	private String combustible;
	private String consumo;
	private String tipoCambio;
	private String categoria;
	private String tipoVehiculo;
	private String traccion;
	private Integer plazas;
	private Integer puertas;
	private String garantia;
	private Integer peso;
	private String color;
	private Integer numeroMarchas;
	private Integer numeroCilindros;
	private String ciudad;
	private String descripcion;
	private Integer telefonoAdjunto;
	private String emailAdjunto;
	private UsuarioDTO usuario;

	public CocheDTO() {

	}

	public CocheDTO(Long id, String marca, String modelo, String imagen_principal, Integer precio, Integer anyo,
			Integer potencia, Integer kilometraje, String combustible, String consumo, String tipoCambio,
			String categoria, String tipoVehiculo, String traccion, Integer plazas, Integer puertas, String garantia,
			Integer peso, String color, Integer numeroMarchas, Integer numeroCilindros, String ciudad,
			String descripcion, Integer telefonoAdjunto, String emailAdjunto, UsuarioDTO usuario) {
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.imagen_principal = imagen_principal;
		this.precio = precio;
		this.anyo = anyo;
		this.potencia = potencia;
		this.kilometraje = kilometraje;
		this.combustible = combustible;
		this.consumo = consumo;
		this.tipoCambio = tipoCambio;
		this.categoria = categoria;
		this.tipoVehiculo = tipoVehiculo;
		this.traccion = traccion;
		this.plazas = plazas;
		this.puertas = puertas;
		this.garantia = garantia;
		this.peso = peso;
		this.color = color;
		this.numeroMarchas = numeroMarchas;
		this.numeroCilindros = numeroCilindros;
		this.ciudad = ciudad;
		this.descripcion = descripcion;
		this.telefonoAdjunto = telefonoAdjunto;
		this.emailAdjunto = emailAdjunto;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getImagen_principal() {
		return imagen_principal;
	}

	public void setImagen_principal(String imagen_principal) {
		this.imagen_principal = imagen_principal;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
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

	public String getCombustible() {
		return combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	public String getConsumo() {
		return consumo;
	}

	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}

	public String getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getTraccion() {
		return traccion;
	}

	public void setTraccion(String traccion) {
		this.traccion = traccion;
	}

	public Integer getPlazas() {
		return plazas;
	}

	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}

	public Integer getPuertas() {
		return puertas;
	}

	public void setPuertas(Integer puertas) {
		this.puertas = puertas;
	}

	public String getGarantia() {
		return garantia;
	}

	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getNumeroMarchas() {
		return numeroMarchas;
	}

	public void setNumeroMarchas(Integer numeroMarchas) {
		this.numeroMarchas = numeroMarchas;
	}

	public Integer getNumeroCilindros() {
		return numeroCilindros;
	}

	public void setNumeroCilindros(Integer numeroCilindros) {
		this.numeroCilindros = numeroCilindros;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public Integer getTelefonoAdjunto() {
		return telefonoAdjunto;
	}

	public void setTelefonoAdjunto(Integer telefonoAdjunto) {
		this.telefonoAdjunto = telefonoAdjunto;
	}

	public String getEmailAdjunto() {
		return emailAdjunto;
	}

	public void setEmailAdjunto(String emailAdjunto) {
		this.emailAdjunto = emailAdjunto;
	}

	@Override
	public String toString() {
		return "CocheDTO [getId()=" + getId() + ", getMarca()=" + getMarca() + ", getModelo()=" + getModelo()
				+ ", getImagen_principal()=" + getImagen_principal() + ", getPrecio()=" + getPrecio() + ", getAnyo()="
				+ getAnyo() + ", getPotencia()=" + getPotencia() + ", getKilometraje()=" + getKilometraje()
				+ ", getCombustible()=" + getCombustible() + ", getConsumo()=" + getConsumo() + ", getTipoCambio()="
				+ getTipoCambio() + ", getCategoria()=" + getCategoria() + ", getTipoVehiculo()=" + getTipoVehiculo()
				+ ", getTraccion()=" + getTraccion() + ", getPlazas()=" + getPlazas() + ", getPuertas()=" + getPuertas()
				+ ", getGarantia()=" + getGarantia() + ", getPeso()=" + getPeso() + ", getColor()=" + getColor()
				+ ", getNumeroMarchas()=" + getNumeroMarchas() + ", getNumeroCilindros()=" + getNumeroCilindros()
				+ ", getCiudad()=" + getCiudad() + ", getDescripcion()=" + getDescripcion() + ", getUsuario()="
				+ getUsuario() + ", getTelefonoAdjunto()=" + getTelefonoAdjunto() + ", getEmailAdjunto()="
				+ getEmailAdjunto() + "]";
	}

}
