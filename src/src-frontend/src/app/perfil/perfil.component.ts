import { Component } from '@angular/core';
import { Usuario } from '../entidad/usuario.model';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ServicioUsuarioService } from '../servicio-usuario/servicio-usuario.service';
import { DataServices } from '../servicio-general/DataServices';
import { AutenticacionService } from '../AutenticacionService/autenticacion.service';
import { UsuarioComponent } from '../usuario/usuario.component';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule, RouterModule, UsuarioComponent],
  providers: [ServicioUsuarioService, DataServices],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css',
})
export class PerfilComponent {
  usuario: Usuario | undefined;
  usuarios: Usuario[] = [];
  estaLogueado: boolean = false;
  mostrarPerfil: boolean = true;
  mostrarGestionUsuarios: boolean = false;

  constructor(
    private servicioAutenticacion: AutenticacionService,
    private servicioUsuario: ServicioUsuarioService
  ) {}

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

    this.obtenerUsuarios();
  }

  mostrarSeccionPerfil(): void {
    this.mostrarPerfil = true;
    this.mostrarGestionUsuarios = false;
  }

  mostrarSeccionGestionUsuarios(): void {
    this.mostrarPerfil = false;
    this.mostrarGestionUsuarios = true;
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

  eliminarUsuario(id: number): void {
    this.servicioUsuario.eliminarUsuario(id);
  }

  editarUsuario(event: { id: number; usuario: Usuario }): void {
    const id = event.id;
    const usuario = event.usuario;
    this.servicioUsuario.actualizarUsuario(id, usuario);
  }
}
