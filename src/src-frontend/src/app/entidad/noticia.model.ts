export class Noticia {
  id: number;
  fecha: Date;
  titulo: string;
  contenido: string;
  imagen: string;
  urlVideo: string;

  constructor(
    id: number,
    fecha: Date,
    titulo: string,
    contenido: string,
    imagen: string,
    urlVideo: string
  ) {
    this.id = id;
    this.fecha = fecha;
    this.titulo = titulo;
    this.contenido = contenido;
    this.imagen = imagen;
    this.urlVideo = urlVideo;
  }
}
