import { Component, OnInit } from '@angular/core';
import { Noticia } from '../entidad/noticia.model';
import { ServicioNoticiaService } from '../servicio-noticia/servicio-noticia.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-actualizar-noticia',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './actualizar-noticia.component.html',
  styleUrl: './actualizar-noticia.component.css',
})
export class ActualizarNoticiaComponent implements OnInit {
  noticias: Noticia[] = [];

  indice!: number;
  cuadroId: number = 0;
  cuadroTitulo: string = '';
  cuadroContenido: string = '';
  cuadroUrlImagen: string = '';
  cuadroUrlVideo: string = '';

  constructor(
    private miServicio: ServicioNoticiaService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.noticias = this.miServicio.noticias;
    this.indice = this.route.snapshot.params['id'];

    this.miServicio
      .cargarNoticiaPorId(this.indice)
      .subscribe((noticia: Noticia) => {
        this.cuadroTitulo = noticia.titulo;
        this.cuadroContenido = noticia.contenido;
        this.cuadroUrlImagen = noticia.url_imagen;
        this.cuadroUrlVideo = noticia.url_video;
      });
  }

  actualizaNoticia() {
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
      this.miServicio.cargarNoticiaPorId(this.indice).subscribe(
        (noticia: Noticia) => {
          if (noticia) {
            // Mostrar alerta de confirmación
            const confirmacion = window.confirm(
              `¿Estás seguro que quieres actualizar la noticia ${noticia.titulo}?`
            );

            // Si el coche confirma la actualización
            if (confirmacion) {
              // Actualizar la noticia
              this.miServicio.actualizarNoticia(this.indice, noticia).subscribe(
                () => {
                  alert('Noticia actualizada correctamente');
                  // Vuelve a la página de gestión
                  this.volverAExplorar();
                  window.scrollTo({ top: 0, behavior: 'smooth' });
                },
                (error) => {
                  if (error.status === 200) {
                    alert('Noticia actualizada correctamente');
                    // Vuelve a la página de gestión
                    this.volverAExplorar();
                    window.scrollTo({ top: 0, behavior: 'smooth' });
                  } else {
                    console.error('Error al eliminar la noticia:', error);
                  }
                }
              );
            }
          } else {
            alert('Error al actualizar la noticia');
            console.error(
              'La noticia con ID',
              this.indice,
              'no fue encontrado.'
            );
          }
        },
        (error) => {
          // Si ocurre un error al cargar la noticia por su ID, se captura aquí
          alert('Error al actualizar la noticia');
          console.error('Error al cargar la noticia:', error);
        }
      );
    }
  }

  volverAExplorar() {
    this.router.navigate(['explorar']);
  }
}
