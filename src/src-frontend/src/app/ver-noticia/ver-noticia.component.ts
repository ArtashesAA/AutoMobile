import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { NoticiaHijoComponent } from '../noticia-hijo/noticia-hijo.component';
import { ServicioNoticiaService } from '../servicio-noticia/servicio-noticia.service';
import { Noticia } from '../entidad/noticia.model';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';

@Component({
  selector: 'app-ver-noticia',
  standalone: true,
  imports: [CommonModule, RouterModule, NoticiaHijoComponent],
  providers: [ServicioNoticiaService],
  templateUrl: './ver-noticia.component.html',
  styleUrl: './ver-noticia.component.css',
})
export class VerNoticiaComponent {
  noticia!: Noticia;
  id!: number;

  constructor(
    private route: ActivatedRoute,
    private servicioNoticia: ServicioNoticiaService,
    private servicioAutenticacion: AutenticacionService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params['id'];

      if (!isNaN(this.id)) {
        this.servicioNoticia.cargarNoticiaPorId(this.id).subscribe(
          (noticia: Noticia) => {
            this.noticia = noticia;
          },
          (error) => {
            console.error('Error al cargar noticia:', error);
          }
        );
      } else {
        console.error('Índice inválido:', this.id);
      }
    });
  }

  estaLogueado() {
    return this.servicioAutenticacion.estaAutenticado();
  }
}
