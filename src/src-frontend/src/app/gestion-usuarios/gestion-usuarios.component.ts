import { Component, Input } from '@angular/core';
import { Usuario } from '../entidad/usuario.model';
import { ServicioUsuarioService } from '../servicio-usuario/servicio-usuario.service';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
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

  // Página de Paginación
  paginaActual: number = 1;

  @Input() usuario!: Usuario;

  constructor(
    private router: Router,
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
      // Cargar el usuario por su ID
      this.servicioUsuario
        .cargarUsuarioPorId(id)
        .subscribe((usuario: Usuario) => {
          if (usuario) {
            // Mostrar alerta de confirmación
            const confirmacion = window.confirm(
              `¿Estás seguro que quieres eliminar el usuario ${usuario.nombre_usuario} ?`
            );

            // Si el usuario confirma la eliminación
            if (confirmacion) {
              // Eliminar el usuario
              this.servicioUsuario.eliminarUsuario(id).subscribe(
                () => {
                  alert('Usuario eliminado correctamente');
                  // Vuelve a la página de gestión
                  this.volverAGestionDeUsuarios();
                },
                (error) => {
                  if (error.status === 200) {
                    alert('Usuario eliminado correctamente');
                    // Vuelve a la página de gestión
                    this.volverAGestionDeUsuarios();
                  } else {
                    console.error('Error al eliminar usuario:', error);
                  }
                }
              );
            }
          } else {
            console.error('El usuario con ID', id, 'no fue encontrado.');
          }
        });
    } else {
      console.error('Error al obtener el ID del usuario.');
    }
  }

  volverAGestionDeUsuarios() {
    this.router.navigate(['gestionUsuarios']);
  }

  // Paginación
  get usuariosPaginados(): Usuario[] {
    const primero = (this.paginaActual - 1) * 10;
    const ultimo = Math.min(primero + 10, this.usuarios.length);
    return this.usuarios.slice(primero, ultimo);
  }

  // Para la página anterior
  anteriorPag() {
    this.paginaActual--;
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  // Para la siguiente página
  siguientePag() {
    this.paginaActual++;
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  // Obtiene el total de páginas dependiendo de los usuarios que se mostrarán en la página
  get totalPaginas(): number {
    return Math.ceil(this.usuarios.length / 10);
  }
}
