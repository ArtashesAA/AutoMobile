import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Usuario } from '../entidad/usuario.model';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-coches-usuario',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './coches-usuario.component.html',
  styleUrl: './coches-usuario.component.css',
})
export class CochesUsuarioComponent {
  usuario: Usuario | undefined;
  estaLogueado: boolean = false;
  esAdmin: boolean = false;
  constructor(
    private servicioAutenticacion: AutenticacionService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.estaLogueado = this.servicioAutenticacion.estaAutenticado();

    if (this.estaLogueado) {
      this.servicioAutenticacion.cargarUsuarioActual().subscribe(
        (usuario: Usuario) => {
          this.usuario = usuario;

          // Comprueba si el usuario es administrador
          this.servicioAutenticacion.esAdmin().subscribe((isAdmin: boolean) => {
            this.esAdmin = isAdmin;
          });
        },
        (error) => {
          console.error('Error al obtener el usuario:', error);
        }
      );
    }
  }
}
