package com.v1.automobile.entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@NotBlank(message = "El nombre de usuario no puede estar en blanco")
	@Column(name = "nombre_usuario", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
	private String nombre_usuario;

	@NotBlank(message = "El email no puede estar en blanco")
	@Column(name = "email", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
	private String email;

	@Column(name = "imagen_usuario", columnDefinition = "VARCHAR(255)")
	private String imagen_usuario;

	@NotBlank(message = "La contraseña no puede estar en blanco")
	@Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
	private String password;

	@Column(name = "role", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'USER'")
	private String role;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "usuario-coches")
	private List<Coche> coches = new ArrayList<>();

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "usuario-noticias")
	private List<Noticia> noticias = new ArrayList<>();

	public Usuario(Integer id,
			@NotBlank(message = "El nombre de usuario no puede estar en blanco") String nombre_usuario,
			@NotBlank(message = "El email no puede estar en blanco") String email, String imagen_usuario,
			@NotBlank(message = "La contraseña no puede estar en blanco") String password, String role,
			List<Coche> coches, List<Noticia> noticias) {
		this.id = id;
		this.nombre_usuario = nombre_usuario;
		this.email = email;
		this.imagen_usuario = imagen_usuario;
		this.password = password;
		this.role = role;
		this.coches = coches;
		this.noticias = noticias;
	}

	public Usuario(Integer id,
			@NotBlank(message = "El nombre de usuario no puede estar en blanco") String nombre_usuario,
			@NotBlank(message = "El email no puede estar en blanco") String email, String imagen_usuario,
			@NotBlank(message = "La contraseña no puede estar en blanco") String password, String role) {
		this.id = id;
		this.nombre_usuario = nombre_usuario;
		this.email = email;
		this.imagen_usuario = imagen_usuario;
		this.password = password;
		this.role = role;
	}

	public Usuario() {

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setEmail(Object email2) {
		// TODO Auto-generated method stub

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getImagen_usuario() {
		return imagen_usuario;
	}

	public void setImagen_usuario(String imagen_usuario) {
		this.imagen_usuario = imagen_usuario;
	}

	public List<Coche> getCoches() {
		return coches;
	}

	public void setCoches(List<Coche> coches) {
		this.coches = coches;
	}

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}

}
