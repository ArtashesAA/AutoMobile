import { Usuario } from './usuario.model';

export class Noticia {
  id?: number;
  titulo: string;
  contenido: string;
  url_imagen: string;
  url_video: string;
  usuario?: Usuario;
  fecha?: Date;

  constructor(
    titulo: string,
    contenido: string,
    url_imagen: string,
    url_video: string,
    usuario?: Usuario,
    fecha?: Date
  ) {
    this.fecha = fecha;
    this.titulo = titulo;
    this.contenido = contenido;
    this.url_imagen = url_imagen;
    this.url_video = url_video;
    this.usuario = usuario;
  }
}
