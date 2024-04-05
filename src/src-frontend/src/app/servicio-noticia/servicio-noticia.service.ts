import { Injectable } from '@angular/core';
import { DataServices } from '../DataServices';
import { ServicioGeneralService } from '../servicio-general/servicio-general.service';

@Injectable({
  providedIn: 'root',
})
export class ServicioNoticiaService {
  constructor(
    private servicioGeneral: ServicioGeneralService,
    private dataService: DataServices
  ) {}

  getNoticia(id: number) {
    return this.dataService.cargarNoticiaPorId(id);
  }

  setNoticias(misNoticias: any) {
    this.noticias = misNoticias;
  }

  obtenerNoticias() {
    return this.dataService.cargarNoticias();
  }

  noticias: any[] = [];

  agregarNoticiaServicio(noticia: any) {
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

  actualizarNoticia(indice: number, noticia: any) {
    let noticiaModificada = this.noticias[indice];

    noticiaModificada.titulo = noticia.titulo;
    noticiaModificada.descripcion = noticia.descripcion;
    noticiaModificada.fecha = noticia.fecha;

    this.dataService.actualizarNoticia(indice, noticia);
  }

  eliminarNoticia(indice: number) {
    this.servicioGeneral.muestraMensaje('Noticia eliminada');
    this.noticias.splice(indice, 1);
    this.dataService.eliminarNoticia(indice);
  }
}
