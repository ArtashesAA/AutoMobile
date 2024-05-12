import { Component } from '@angular/core';
import { Usuario } from '../entidad/usuario.model';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ServicioUsuarioService } from '../servicio-usuario/servicio-usuario.service';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule, RouterModule],
  providers: [ServicioUsuarioService],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css',
})
export class PerfilComponent {
  usuario: Usuario | undefined;
  estaLogueado: boolean = false;

  constructor(private servicioAutenticacion: AutenticacionService) {}

  ngOnInit(): void {
    this.estaLogueado = this.servicioAutenticacion.estaAutenticado();

    if (this.estaLogueado) {
      this.servicioAutenticacion.cargarUsuarioActual().subscribe(
        (usuario: Usuario) => {
          this.usuario = usuario;
        },
        (error) => {
          console.error('Error al obtener el usuario:', error);
        }
      );
    }
  }

  esAdmin() {
    this.servicioAutenticacion.esAdmin();
  }
}
