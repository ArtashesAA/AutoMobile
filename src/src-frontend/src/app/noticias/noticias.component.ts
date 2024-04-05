import { Component } from '@angular/core';
import { Noticia } from '../entidad/noticia.model';
import { ServicioNoticiaService } from '../servicio-noticia/servicio-noticia.service';
import { NoticiaHijoComponent } from '../noticia-hijo/noticia-hijo.component';
import { CommonModule } from '@angular/common';
import { DataServices } from '../DataServices';

@Component({
  selector: 'app-noticias',
  standalone: true,
  imports: [NoticiaHijoComponent, CommonModule],
  providers: [ServicioNoticiaService, DataServices],
  templateUrl: './noticias.component.html',
  styleUrl: './noticias.component.css',
})
export class NoticiasComponent {
  titulo = 'Novedades';

  noticias: Noticia[] = [];

  constructor(private miServicio: ServicioNoticiaService) {}

  ngOnInit(): void {
    this.miServicio.obtenerNoticias().subscribe((misNoticias) => {
      console.log(misNoticias);
      this.noticias = Object.values(misNoticias);

      this.miServicio.setNoticias(this.noticias);
    });
  }
}
