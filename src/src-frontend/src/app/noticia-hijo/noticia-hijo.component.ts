import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Noticia } from '../entidad/noticia.model';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';
import { ServicioNoticiaService } from '../servicio-noticia/servicio-noticia.service';

@Component({
  selector: 'app-noticia-hijo',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './noticia-hijo.component.html',
  styleUrl: './noticia-hijo.component.css',
})
export class NoticiaHijoComponent {
  @Input() noticiadelista!: Noticia;
  @Input() indiceNoticia!: number;

  constructor(
    private router: Router,
    private servicioAutenticacion: AutenticacionService,
    private servicioNoticia: ServicioNoticiaService
  ) {}

  ngOnInit(): void {}

  estaLogueado() {
    return this.servicioAutenticacion.estaAutenticado();
  }

  // Borra un coche por su id
  eliminarNoticia(id: number) {
    if (id != null) {
      // Cargar el noticia por su ID
      this.servicioNoticia
        .cargarNoticiaPorId(id)
        .subscribe((noticia: Noticia) => {
          if (noticia) {
            // Mostrar alerta de confirmación
            const confirmacion = window.confirm(
              `¿Estás seguro que quieres eliminar la noticia ${noticia.titulo} ?`
            );

            // Si el usuario confirma la eliminación
            if (confirmacion) {
              // Eliminar la noticia
              this.servicioNoticia.eliminarNoticia(id).subscribe(
                () => {
                  alert('Noticia eliminada correctamente');
                  // Refrescar la página
                  this.router.navigate(['/explorar']);
                  window.scrollTo({ top: 0, behavior: 'smooth' });
                },
                (error) => {
                  if (error.status === 200) {
                    alert('Noticia eliminada correctamente');
                    // Refrescar la página
                    this.router.navigate(['/explorar']);
                    window.scrollTo({ top: 0, behavior: 'smooth' });
                  } else {
                    console.error('Error al eliminar la noticia:', error);
                    alert('Error al eliminar la noticia');
                  }
                }
              );
            }
          } else {
            console.error('La noticia con ID', id, 'no fue encontrado.');
            alert('Error al eliminar la noticia');
          }
        });
    } else {
      console.error('Error al obtener el ID de la noticia.');
      alert('Error al eliminar la noticia');
    }
  }
}
