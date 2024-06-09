import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { ServicioNoticiaService } from '../servicio-noticia/servicio-noticia.service';
import { Noticia } from '../entidad/noticia.model';
import { ServicioUsuarioService } from '../servicio-usuario/servicio-usuario.service';

@Component({
  selector: 'app-publicar-noticia',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './publicar-noticia.component.html',
  styleUrl: './publicar-noticia.component.css',
})
export class PublicarNoticiaComponent {
  noticias: Noticia[] = [];
  noticia: Noticia | undefined;

  cuadroTitulo: string = '';
  cuadroContenido: string = '';
  cuadroUrlImagen: string = '';
  cuadroUrlVideo: string = '';

  constructor(
    private miServicio: ServicioNoticiaService,
    private router: Router,
    private servicioUsuario: ServicioUsuarioService
  ) {}

  publicarNoticia() {
    // Verificar que todos los campos estén completos
    if (
      !this.cuadroTitulo ||
      !this.cuadroContenido ||
      !this.cuadroUrlImagen ||
      !this.cuadroUrlVideo
    ) {
      alert('Debe rellenar todos los datos.');
      return;
    }

    if (
      this.cuadroTitulo &&
      this.cuadroContenido &&
      this.cuadroUrlImagen &&
      this.cuadroUrlVideo
    ) {
      let noticia = new Noticia(
        this.cuadroTitulo,
        this.cuadroContenido,
        this.cuadroUrlImagen,
        this.cuadroUrlVideo
      );

      // Cargar el noticia por su ID
      this.miServicio.crearNoticia(noticia).subscribe(
        (response) => {
          if (response.status === 200 || response.status === 201) {
            alert('Noticia creada con éxito.');
            // Redirigir a la página de creación correcta
            this.router.navigate(['/publicarNoticiaCorrecto']);
          } else {
            alert('Error al crear la noticia.');
          }
        },
        (error) => {
          if (error.status === 201) {
            // Si la respuesta es 201 (creación exitosa), redirigir a la página de creación correcta
            this.router.navigate(['/publicarNoticiaCorrecto']);
          } else {
            console.error('Error al crear la noticia:', error);
            alert('Error al crear la noticia.');
          }
        }
      );
    }
  }

  volverAExplorar() {
    this.router.navigate(['explorar']);
  }
}
