package com.v1.automobile.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "coche")
public class Coche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank(message = "La marca no puede estar en blanco")
	@Column(name = "marca", nullable = false)
	private String marca;

	@NotBlank(message = "El modelo no puede estar en blanco")
	@Column(name = "modelo", nullable = false)
	private String modelo;

	@Column(name = "imagen_principal")
	private String imagen_principal;

	@NotNull(message = "El precio no puede ser nulo")
	@Column(name = "precio", nullable = false)
	private Integer precio;

	@NotNull(message = "El año no puede ser nulo")
	@Column(name = "anyo", nullable = false)
	private Integer anyo;

	@NotNull(message = "La potencia no puede ser nula")
	@Column(name = "potencia", nullable = false)
	private Integer potencia;

	@NotNull(message = "El kilometraje no puede ser nulo")
	@Column(name = "kilometraje", nullable = false)
	private Integer kilometraje;

	@NotBlank(message = "El combustible no puede estar en blanco")
	@Column(name = "combustible", nullable = false)
	private String combustible;

	@NotBlank(message = "El consumo no puede estar en blanco")
	@Column(name = "consumo", nullable = false)
	private String consumo;

	@NotBlank(message = "El tipo de cambio no puede estar en blanco")
	@Column(name = "tipo_cambio", nullable = false)
	private String tipoCambio;

	@NotBlank(message = "La categoría no puede estar en blanco")
	@Column(name = "categoria", nullable = false)
	private String categoria;

	@NotBlank(message = "El tipo de vehículo no puede estar en blanco")
	@Column(name = "tipo_vehiculo", nullable = false)
	private String tipoVehiculo;

	@NotBlank(message = "La tracción no puede estar en blanco")
	@Column(name = "traccion", nullable = false)
	private String traccion;

	@NotNull(message = "El número de plazas no puede ser nulo")
	@Column(name = "plazas", nullable = false)
	private Integer plazas;

	@NotNull(message = "El número de puertas no puede ser nulo")
	@Column(name = "puertas", nullable = false)
	private Integer puertas;

	@NotBlank(message = "La garantía no puede estar en blanco")
	@Column(name = "garantia", nullable = false)
	private String garantia;

	@NotNull(message = "El peso no puede ser nulo")
	@Column(name = "peso", nullable = false)
	private Integer peso;

	@NotBlank(message = "El color no puede estar en blanco")
	@Column(name = "color", nullable = false)
	private String color;

	@NotNull(message = "El número de marchas no puede ser nulo")
	@Column(name = "numero_marchas", nullable = false)
	private Integer numeroMarchas;

	@NotNull(message = "El número de cilindros no puede ser nulo")
	@Column(name = "numero_cilindros", nullable = false)
	private Integer numeroCilindros;

	@NotBlank(message = "La ciudad no puede estar en blanco")
	@Column(name = "ciudad", nullable = false)
	private String ciudad;

	@NotBlank(message = "La descripción no puede estar en blanco")
	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@NotBlank(message = "El telefono no puede estar en blanco")
	@Column(name = "telefonoAdjunto")
	private Integer telefonoAdjunto;

	@NotBlank(message = "El email no puede estar en blanco")
	@Column(name = "emailAdjunto")
	private String emailAdjunto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
	private Usuario usuario;

	public Coche() {

	}

	public Coche(Long id, @NotBlank(message = "La marca no puede estar en blanco") String marca,
			@NotBlank(message = "El modelo no puede estar en blanco") String modelo, String imagen_principal,
			@NotNull(message = "El precio no puede ser nulo") Integer precio,
			@NotNull(message = "El año no puede ser nulo") Integer anyo,
			@NotNull(message = "La potencia no puede ser nula") Integer potencia,
			@NotNull(message = "El kilometraje no puede ser nulo") Integer kilometraje,
			@NotBlank(message = "El combustible no puede estar en blanco") String combustible,
			@NotBlank(message = "El consumo no puede estar en blanco") String consumo,
			@NotBlank(message = "El tipo de cambio no puede estar en blanco") String tipoCambio,
			@NotBlank(message = "La categoría no puede estar en blanco") String categoria,
			@NotBlank(message = "El tipo de vehículo no puede estar en blanco") String tipoVehiculo,
			@NotBlank(message = "La tracción no puede estar en blanco") String traccion,
			@NotNull(message = "El número de plazas no puede ser nulo") Integer plazas,
			@NotNull(message = "El número de puertas no puede ser nulo") Integer puertas,
			@NotBlank(message = "La garantía no puede estar en blanco") String garantia,
			@NotNull(message = "El peso no puede ser nulo") Integer peso,
			@NotBlank(message = "El color no puede estar en blanco") String color,
			@NotNull(message = "El número de marchas no puede ser nulo") Integer numeroMarchas,
			@NotNull(message = "El número de cilindros no puede ser nulo") Integer numeroCilindros,
			@NotBlank(message = "La ciudad no puede estar en blanco") String ciudad,
			@NotBlank(message = "La descripción no puede estar en blanco") String descripcion,
			@NotBlank(message = "El telefono no puede estar en blanco") Integer telefonoAdjunto,
			@NotBlank(message = "El email no puede estar en blanco") String emailAdjunto, Usuario usuario) {
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Coche [getId()=" + getId() + ", getMarca()=" + getMarca() + ", getModelo()=" + getModelo()
				+ ", getImagen_principal()=" + getImagen_principal() + ", getPrecio()=" + getPrecio() + ", getAnyo()="
				+ getAnyo() + ", getPotencia()=" + getPotencia() + ", getKilometraje()=" + getKilometraje()
				+ ", getCombustible()=" + getCombustible() + ", getConsumo()=" + getConsumo() + ", getTipoCambio()="
				+ getTipoCambio() + ", getCategoria()=" + getCategoria() + ", getTipoVehiculo()=" + getTipoVehiculo()
				+ ", getTraccion()=" + getTraccion() + ", getPlazas()=" + getPlazas() + ", getPuertas()=" + getPuertas()
				+ ", getGarantia()=" + getGarantia() + ", getPeso()=" + getPeso() + ", getColor()=" + getColor()
				+ ", getNumeroMarchas()=" + getNumeroMarchas() + ", getNumeroCilindros()=" + getNumeroCilindros()
				+ ", getCiudad()=" + getCiudad() + ", getDescripcion()=" + getDescripcion() + ", getTelefonoAdjunto()="
				+ getTelefonoAdjunto() + ", getEmailAdjunto()=" + getEmailAdjunto() + ", getUsuario()=" + getUsuario()
				+ "]";
	}

}
