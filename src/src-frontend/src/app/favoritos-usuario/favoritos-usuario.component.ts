import { Component } from '@angular/core';

import { Coche } from '../entidad/coche.model';
import { Usuario } from '../entidad/usuario.model';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CocheHijoComponent } from '../coche-hijo/coche-hijo.component';
import { ServicioFavoritoService } from '../servicio-favorito/servicio-favorito.service';
import { Favorito } from '../entidad/favorito.model';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';

@Component({
  selector: 'app-favoritos-usuario',
  standalone: true,
  imports: [CommonModule, RouterModule, CocheHijoComponent],
  templateUrl: './favoritos-usuario.component.html',
  styleUrl: './favoritos-usuario.component.css',
})
export class FavoritosUsuarioComponent {
  favoritos: Favorito[] = [];
  cochesFavoritos: Coche[] = [];
  usuario: Usuario | undefined;
  estaLogueado: boolean = false;
  esAdmin: boolean = false;

  constructor(
    private servicioFavorito: ServicioFavoritoService,
    private servicioAutenticacion: AutenticacionService,
    private servicioCoche: ServicioCocheService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.estaLogueado = this.servicioAutenticacion.estaAutenticado();

    if (this.estaLogueado) {
      this.servicioAutenticacion.cargarUsuarioActual().subscribe(
        (usuario: Usuario) => {
          this.usuario = usuario;

          this.servicioAutenticacion.esAdmin().subscribe((isAdmin: boolean) => {
            this.esAdmin = isAdmin;
          });

          // Cargar coches favoritos del usuario actual
          this.cargarCochesFavoritosPorIdUsuario(this.usuario.id);
        },
        (error) => {
          console.error('Error al obtener el usuario:', error);
        }
      );
    }
  }

  cargarCochesFavoritosPorIdUsuario(idUsuario: number): void {
    this.servicioFavorito.cargarFavoritosPorIdUsuario(idUsuario).subscribe(
      (favoritos: Favorito[]) => {
        this.favoritos = favoritos;
        this.obtenerCochesPorFavoritos();
      },
      (error) => {
        console.error('Error al cargar coches favoritos del usuario:', error);
      }
    );
  }

  obtenerCochesPorFavoritos(): void {
    // Iterar sobre cada favorito y obtener el coche por su ID
    this.favoritos.forEach((favorito) => {
      if (favorito.coche?.id) {
        this.servicioCoche.cargarCochePorId(favorito.coche.id).subscribe(
          (coche: Coche) => {
            this.cochesFavoritos.push(coche);
          },
          (error) => {
            console.error('Error al cargar coche por ID:', error);
          }
        );
      }
    });
  }

  irACatalogo(): void {
    this.router.navigate(['/catalogo']);
  }
}
