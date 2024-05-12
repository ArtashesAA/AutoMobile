import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Usuario } from '../entidad/usuario.model';
import { ServicioUsuarioService } from '../servicio-usuario/servicio-usuario.service';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule, CommonModule],
  providers: [ServicioUsuarioService],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent implements OnInit {
  estaLogueado: boolean = false;
  usuario: Usuario | undefined;

  constructor(private servicioAutenticacion: AutenticacionService) {}

  // Si esta logueado, muestra el nombre del usuario en el header
  ngOnInit(): void {
    // Comprueba que esté logueado
    this.estaLogueado = this.servicioAutenticacion.estaAutenticado();

    // Si está logueado, carga los datos del usuario que ha iniciado sesión
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

  // Elimina el token y refresca la página
  logout(): void {
    this.servicioAutenticacion.eliminarToken();
    window.location.reload();
  }
}
