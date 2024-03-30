package com.v1.automobile.entidad;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "noticia")
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "contenido", nullable = false)
    private String contenido;

    @Column(name = "url_video")
    private String urlVideo;

    @OneToMany(mappedBy = "noticia", cascade = CascadeType.ALL)
    private List<ContenidoNoticia> contenidoNoticiaList;
    
    public Noticia() {
    	
    }

	public Noticia(Long id, Date fecha, String titulo, String contenido, String urlVideo,
			List<ContenidoNoticia> contenidoNoticiaList) {
		this.id = id;
		this.fecha = fecha;
		this.titulo = titulo;
		this.contenido = contenido;
		this.urlVideo = urlVideo;
		this.contenidoNoticiaList = contenidoNoticiaList;
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

	public String getUrlVideo() {
		return urlVideo;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

	public List<ContenidoNoticia> getContenidoNoticiaList() {
		return contenidoNoticiaList;
	}

	public void setContenidoNoticiaList(List<ContenidoNoticia> contenidoNoticiaList) {
		this.contenidoNoticiaList = contenidoNoticiaList;
	}

	@Override
	public String toString() {
		return "Noticia [getId()=" + getId() + ", getFecha()=" + getFecha() + ", getTitulo()=" + getTitulo()
				+ ", getContenido()=" + getContenido() + ", getUrlVideo()=" + getUrlVideo()
				+ ", getContenidoNoticiaList()=" + getContenidoNoticiaList() + "]";
	}
}
