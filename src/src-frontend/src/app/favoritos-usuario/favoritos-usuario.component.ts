import { Component } from '@angular/core';

import { Coche } from '../entidad/coche.model';
import { Usuario } from '../entidad/usuario.model';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CocheHijoComponent } from '../coche-hijo/coche-hijo.component';

@Component({
  selector: 'app-favoritos-usuario',
  standalone: true,
  imports: [CommonModule, RouterModule, CocheHijoComponent],
  templateUrl: './favoritos-usuario.component.html',
  styleUrl: './favoritos-usuario.component.css',
})
export class FavoritosUsuarioComponent {
  cochesUsuario: Coche[] = [];

  usuario: Usuario | undefined;

  estaLogueado: boolean = false;
  esAdmin: boolean = false;

  constructor(
    private servicioCoche: ServicioCocheService,
    private servicioAutenticacion: AutenticacionService,
    private router: Router
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

          // Cargar coches del usuario actual
          this.cargarCochesPorIdUsuario(this.usuario.id);
        },
        (error) => {
          console.error('Error al obtener el usuario:', error);
        }
      );
    }
  }

  cargarCochesPorIdUsuario(idUsuario: number): void {
    this.servicioCoche.cargarCochesPorIdUsuario(idUsuario).subscribe(
      (coches: Coche[]) => {
        this.cochesUsuario = coches;
      },
      (error) => {
        console.error('Error al cargar coches del usuario:', error);
      }
    );
  }

  irACatalogo() {
    this.router.navigate(['/catalogo']);
  }
}
