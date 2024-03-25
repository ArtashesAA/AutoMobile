package com.v1.automobile.entidad;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	@NotNull(message = "El año no puede ser nulo")
	@Column(name = "anyo", nullable = false)
	private Integer anyo;

	@NotNull(message = "La potencia no puede ser nula")
	@Column(name = "potencia", nullable = false)
	private Integer potencia;

	@NotNull(message = "El kilometraje no puede ser nulo")
	@Column(name = "kilometraje", nullable = false)
	private Integer kilometraje;

	@NotNull(message = "El peso no puede ser nulo")
	@Column(name = "peso", nullable = false)
	private Integer peso;

	@NotNull(message = "El combustible no puede ser nulo")
	@Column(name = "combustible", nullable = false)
	private String combustible;

	@NotNull(message = "El color no puede ser nulo")
	@Column(name = "color", nullable = false)
	private String color;

	@NotNull(message = "El precio no puede ser nulo")
	@Column(name = "precio", nullable = false)
	private Integer precio;

	@NotNull(message = "La descripción no puede estar vacía")
	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
	private Usuario usuario;

	@OneToMany(mappedBy = "coche", cascade = CascadeType.ALL)
	private List<Imagen> imagenes = new ArrayList<>();

	public Coche() {

	}

	public Coche(Long id, @NotBlank(message = "La marca no puede estar en blanco") String marca,
			@NotBlank(message = "El modelo no puede estar en blanco") String modelo,
			@NotNull(message = "El año no puede ser nulo") Integer anyo,
			@NotNull(message = "La potencia no puede ser nula") Integer potencia,
			@NotNull(message = "El kilometraje no puede ser nulo") Integer kilometraje,
			@NotNull(message = "El peso no puede ser nulo") Integer peso,
			@NotNull(message = "El combustible no puede ser nulo") String combustible,
			@NotNull(message = "El color no puede ser nulo") String color,
			@NotNull(message = "El precio no puede ser nulo") Integer precio,
			@NotNull(message = "La descripción no puede estar vacía") String descripcion, Usuario usuario,
			List<Imagen> imagenes) {
		this.id = id;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}

	@Override
	public String toString() {
		return "Coche [getId()=" + getId() + ", getMarca()=" + getMarca() + ", getModelo()=" + getModelo()
				+ ", getAnyo()=" + getAnyo() + ", getPotencia()=" + getPotencia() + ", getKilometraje()="
				+ getKilometraje() + ", getPeso()=" + getPeso() + ", getCombustible()=" + getCombustible()
				+ ", getColor()=" + getColor() + ", getDescripcion()=" + getDescripcion() + ", getPrecio()="
				+ getPrecio() + ", getUsuario()=" + getUsuario() + ", getImagenes()=" + getImagenes() + "]";
	}

}
