import { Component, Input } from '@angular/core';
import { Usuario } from '../entidad/usuario.model';
import { ServicioUsuarioService } from '../servicio-usuario/servicio-usuario.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';

@Component({
  selector: 'app-gestion-usuarios',
  standalone: true,
  imports: [CommonModule, RouterModule],
  providers: [ServicioUsuarioService],
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
    // Almacena un true si está logueado
    this.estaLogueado = this.servicioAutenticacion.estaAutenticado();

    // Si está logueado, obtiene los usuarios
    if (this.estaLogueado) {
      this.obtenerUsuarios();
    }
  }

  // Recupera todos los usuarios
  obtenerUsuarios(): void {
    this.servicioUsuario.cargarUsuarios().subscribe(
      (usuarios) => {
        this.usuarios = usuarios;
      },
      (error) => {
        console.error('Error al obtener usuarios:', error);
      }
    );
  }

  // Borra un usuario por su id
  eliminarUsuario(id: number) {
    if (id != null) {
      this.servicioUsuario.eliminarUsuario(id);
    } else {
      console.error('Error al obtener el id del usuario.');
    }
  }
}
