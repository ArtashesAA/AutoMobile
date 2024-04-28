import { Component, Input } from '@angular/core';
import { Usuario } from '../entidad/usuario.model';
import { ServicioUsuarioService } from '../servicio-usuario/servicio-usuario.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { DataServices } from '../servicio-general/DataServices';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';

@Component({
  selector: 'app-gestion-usuarios',
  standalone: true,
  imports: [CommonModule, RouterModule],
  providers: [ServicioUsuarioService, DataServices],
  templateUrl: './gestion-usuarios.component.html',
  styleUrl: './gestion-usuarios.component.css',
})
export class GestionUsuariosComponent {
  usuarios: Usuario[] = [];
  estaLogueado: boolean = false;

  @Input() usuario!: Usuario;

  constructor(
    private servicioAutenticacion: AutenticacionService,
    private servicioUsuario: ServicioUsuarioService
  ) {}

  ngOnInit(): void {
    this.estaLogueado = this.servicioAutenticacion.estaAutenticado();

    if (this.estaLogueado) {
      this.obtenerUsuarios();
    }
  }

  obtenerUsuarios(): void {
    this.servicioUsuario.getUsuarios().subscribe(
      (usuarios) => {
        this.usuarios = usuarios;
      },
      (error) => {
        console.error('Error al obtener usuarios:', error);
      }
    );
  }
}
