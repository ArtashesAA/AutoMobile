import { Component, Input } from '@angular/core';
import { Coche } from '../entidad/coche.model';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';

@Component({
  selector: 'app-coche-hijo',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './coche-hijo.component.html',
  styleUrl: './coche-hijo.component.css',
})
export class CocheHijoComponent {
  @Input() cochedelista!: Coche;

  constructor(
    private servicioAutenticacion: AutenticacionService,
    private servicioCoche: ServicioCocheService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  estaLogueado() {
    return this.servicioAutenticacion.estaAutenticado();
  }

  // Borra un coche por su id
  eliminarCoche(id: number) {
    if (id != null) {
      // Cargar el coche por su ID
      this.servicioCoche.cargarCochePorId(id).subscribe((coche: Coche) => {
        if (coche) {
          // Mostrar alerta de confirmación
          const confirmacion = window.confirm(
            `¿Estás seguro que quieres eliminar el coche ${coche.marca} ${coche.modelo}?`
          );

          // Si el usuario confirma la eliminación
          if (confirmacion) {
            // Eliminar el coche
            this.servicioCoche.eliminarCoche(id).subscribe(
              () => {
                alert('Coche eliminado correctamente');
                // Refrescar la página
                this.router.navigate(['/catalogo']);
              },
              (error) => {
                if (error.status === 200) {
                  alert('Coche eliminado correctamente');
                  // Refrescar la página
                  this.router.navigate(['/catalogo']);
                } else {
                  console.error('Error al eliminar coche:', error);
                }
              }
            );
          }
        } else {
          console.error('El coche con ID', id, 'no fue encontrado.');
        }
      });
    } else {
      console.error('Error al obtener el ID del coche.');
    }
  }
}
