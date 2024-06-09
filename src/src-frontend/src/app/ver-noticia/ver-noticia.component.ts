import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { NoticiaHijoComponent } from '../noticia-hijo/noticia-hijo.component';
import { ServicioNoticiaService } from '../servicio-noticia/servicio-noticia.service';
import { Noticia } from '../entidad/noticia.model';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';
import { Usuario } from '../entidad/usuario.model';

@Component({
  selector: 'app-ver-noticia',
  standalone: true,
  imports: [CommonModule, RouterModule, NoticiaHijoComponent],
  providers: [ServicioNoticiaService, NoticiaHijoComponent],
  templateUrl: './ver-noticia.component.html',
  styleUrl: './ver-noticia.component.css',
})
export class VerNoticiaComponent {
  noticia!: Noticia;
  id!: number;

  // Comprueba si esta logueado y si es admin
  estaLogueado: boolean = false;
  esAdmin: boolean = false;
  usuario: Usuario | undefined;

  constructor(
    private route: ActivatedRoute,
    private servicioNoticia: ServicioNoticiaService,
    private servicioAutenticacion: AutenticacionService,
    private noticiaHijo: NoticiaHijoComponent
  ) {}

  // Carga la noticia por id y almacena esAdmin y estaLogueado
  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params['id'];

      if (!isNaN(this.id)) {
        this.servicioNoticia.cargarNoticiaPorId(this.id).subscribe(
          (noticia: Noticia) => {
            this.noticia = noticia;
          },
          (error) => {
            console.error('Error al cargar noticia:', error);
          }
        );
      } else {
        console.error('Índice inválido:', this.id);
      }
    });

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

  // Borra una noticia por su id
  eliminarNoticia() {
    if (this.id !== undefined) {
      this.noticiaHijo.eliminarNoticia(this.id);
    } else {
      console.error('El ID de la noticia es indefinido.');
    }
  }
}
