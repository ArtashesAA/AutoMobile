import { Component } from '@angular/core';
import { Noticia } from '../entidad/noticia.model';
import { ServicioNoticiaService } from '../servicio-noticia/servicio-noticia.service';
import { NoticiaHijoComponent } from '../noticia-hijo/noticia-hijo.component';
import { CommonModule } from '@angular/common';
import { DataServices } from '../servicio-general/DataServices';

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

  constructor(private dataService: DataServices) {}

  ngOnInit(): void {
    this.cargarNoticias();
  }

  cargarNoticias(): void {
    this.dataService.cargarNoticias().subscribe(
      (response) => {
        this.noticias = response;
        console.log('Noticias cargadas:', this.noticias);
      },
      (error) => {
        console.error('Error al cargar noticias:', error);
      }
    );
  }
}
