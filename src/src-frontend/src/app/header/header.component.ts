import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from '../login/servicio/login.service';
import { CommonModule } from '@angular/common';
import { Usuario } from '../entidad/usuario.model';
import { ServicioUsuarioService } from '../servicio-usuario/servicio-usuario.service';
import { DataServices } from '../servicio-general/DataServices';
import { AutenticacionService } from '../AutenticacionService/autenticacion.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule, CommonModule],
  providers: [ServicioUsuarioService, DataServices],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent implements OnInit {
  estaLogueado: boolean = false;
  usuario: Usuario | undefined;

  constructor(
    private router: Router,
    private servicioAutenticacion: AutenticacionService
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
  }

  logout(): void {
    this.servicioAutenticacion.eliminarToken();
    this.router.navigate(['/']);
  }
}
