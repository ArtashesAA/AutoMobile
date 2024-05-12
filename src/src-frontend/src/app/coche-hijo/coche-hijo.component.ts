import { Component, Input } from '@angular/core';
import { Coche } from '../entidad/coche.model';
import { RouterModule } from '@angular/router';
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
    private servicioCoche: ServicioCocheService
  ) {}

  ngOnInit(): void {}

  estaLogueado() {
    return this.servicioAutenticacion.estaAutenticado();
  }

  // Borra un coche por su id
  eliminarCoche(id: number) {
    if (id != null) {
      // Cargar el coche por su ID
      this.servicioCoche.cargarCochePorId(id).subscribe(
        (coche: Coche) => {
          if (coche) {
            // Si el coche existe y no es null, eliminarlo
            this.servicioCoche.eliminarCoche(id).subscribe(
              () => {
                console.log('Coche eliminado correctamente');
              },
              (error) => {
                console.error('Error al eliminar el coche:', error);
              }
            );
          } else {
            console.error('El coche con ID', id, 'no fue encontrado.');
          }
        },
        (error) => {
          console.error('Error al cargar el coche:', error);
        }
      );
    } else {
      console.error('Error al obtener el ID del coche.');
    }
  }
}
