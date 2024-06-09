import { Component } from '@angular/core';
import { Noticia } from '../entidad/noticia.model';
import { ServicioNoticiaService } from '../servicio-noticia/servicio-noticia.service';
import { NoticiaHijoComponent } from '../noticia-hijo/noticia-hijo.component';
import { CommonModule } from '@angular/common';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';
import { Usuario } from '../entidad/usuario.model';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-noticias',
  standalone: true,
  imports: [NoticiaHijoComponent, CommonModule, RouterModule],
  providers: [ServicioNoticiaService],
  templateUrl: './noticias.component.html',
  styleUrl: './noticias.component.css',
})
export class NoticiasComponent {
  // Almacena si esta logueado o no
  estaLogueado: boolean = false;

  // Almacena si es admin o no
  esAdmin: boolean = false;

  // Almacena el usuario actual
  usuario: Usuario | undefined;

  noticias: Noticia[] = [];

  constructor(
    private servicioNoticia: ServicioNoticiaService,
    private servicioAutenticacion: AutenticacionService
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

    this.cargarNoticias();
  }

  cargarNoticias(): void {
    this.servicioNoticia.cargarNoticias().subscribe(
      (response) => {
        this.noticias = response;
        console.log('Noticias cargadas:', this.noticias);
      },
      (error) => {
        console.error('Error al cargar noticias:', error);
      }
    );
  }
}
