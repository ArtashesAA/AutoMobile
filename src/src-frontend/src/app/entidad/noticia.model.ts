import { Usuario } from './usuario.model';

export class Noticia {
  id: number;
  fecha: Date;
  titulo: string;
  contenido: string;
  url_imagen: string;
  url_video: string;
  usuario: Usuario;

  constructor(
    id: number,
    fecha: Date,
    titulo: string,
    contenido: string,
    url_imagen: string,
    url_video: string,
    usuario: Usuario
  ) {
    this.id = id;
    this.fecha = fecha;
    this.titulo = titulo;
    this.contenido = contenido;
    this.url_imagen = url_imagen;
    this.url_video = url_video;
    this.usuario = usuario;
  }
}
