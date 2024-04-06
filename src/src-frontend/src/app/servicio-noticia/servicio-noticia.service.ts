import { Injectable } from '@angular/core';
import { DataServices } from '../servicio-general/DataServices';
import { ServicioGeneralService } from '../servicio-general/servicio-general.service';
import { Noticia } from '../entidad/noticia.model';

@Injectable({
  providedIn: 'root',
})
export class ServicioNoticiaService {
  noticias: Noticia[] = [];

  constructor(
    private servicioGeneral: ServicioGeneralService,
    private dataService: DataServices
  ) {}

  getNoticia(id: number) {
    return this.dataService.cargarNoticiaPorId(id);
  }

  setNoticias(misNoticias: Noticia[]) {
    this.noticias = misNoticias;
  }

  obtenerNoticias() {
    return this.dataService.cargarNoticias();
  }

  agregarNoticiaServicio(noticia: Noticia) {
    this.servicioGeneral.muestraMensaje(
      'Noticia que se va a agregar: ' + '\n' + noticia.titulo
    );
    this.noticias.push(noticia);
    if (this.noticias.length === 1) {
      this.dataService.guardarNoticias(this.noticias);
    } else {
      this.dataService.guardarNoticias(this.noticias);
    }
  }

  actualizarNoticia(indice: number, noticia: Noticia) {
    let noticiaModificada = this.noticias[indice];

    noticiaModificada.titulo = noticia.titulo;
    noticiaModificada.contenido = noticia.contenido;
    noticiaModificada.imagen = noticia.imagen;

    this.dataService.actualizarNoticia(indice, noticia);
  }

  eliminarNoticia(indice: number) {
    this.servicioGeneral.muestraMensaje('Noticia eliminada');
    this.noticias.splice(indice, 1);
    this.dataService.eliminarNoticia(indice);
  }
}
