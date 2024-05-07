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
      this.servicioCoche.eliminarCoche(id);
    } else {
      console.error('Error al obtener el id del coche.');
    }
  }
}
