import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from '../entidad/usuario.model';
import { ServicioUsuarioService } from '../servicio-usuario/servicio-usuario.service';
import { GestionUsuariosComponent } from '../gestion-usuarios/gestion-usuarios.component';

@Component({
  selector: 'app-actualizar-usuario',
  standalone: true,
  imports: [FormsModule, GestionUsuariosComponent],
  providers: [ServicioUsuarioService],
  templateUrl: './actualizar-usuario.component.html',
  styleUrl: './actualizar-usuario.component.css',
})
export class ActualizarUsuarioComponent implements OnInit {
  cuadroId: number = 0;
  cuadroNombreUsuario: string = '';
  cuadroEmail: string = '';
  cuadroImagenUsuario: string = '';
  cuadroPassword: string = '';
  cuadroRole: string = '';

  indice!: number;
  usuarios: Usuario[] = [];

  constructor(
    private router: Router,
    private miServicio: ServicioUsuarioService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      const id = +params['id'];
      this.indice = id;
      this.miServicio.cargarUsuarioPorId(id).subscribe((usuario: Usuario) => {
        this.cuadroId = usuario.id;
        this.cuadroNombreUsuario = usuario.nombre_usuario;
        this.cuadroEmail = usuario.email;
        this.cuadroImagenUsuario = usuario.imagen_usuario;
        this.cuadroPassword = usuario.password;
        this.cuadroRole = usuario.role;
      });
    });

    const botonVolverAGestionUsuarios = document.getElementById(
      'boton-volver-gestion-usuarios'
    );
    if (botonVolverAGestionUsuarios) {
      botonVolverAGestionUsuarios.addEventListener(
        'click',
        this.volverAGestionDeUsuarios.bind(this)
      );
    }
  }

  actualizaUsuario() {
    let miUsuario = new Usuario(
      this.cuadroId,
      this.cuadroNombreUsuario,
      this.cuadroEmail,
      this.cuadroImagenUsuario,
      this.cuadroPassword,
      this.cuadroRole
    );

    // Cargar el usuario por su ID
    this.miServicio.cargarUsuarioPorId(this.indice).subscribe(
      (usuario: Usuario) => {
        if (usuario) {
          // Mostrar alerta de confirmación
          const confirmacion = window.confirm(
            `¿Estás seguro que quieres actualizar el usuario ${usuario.nombre_usuario}?`
          );

          // Si el usuario confirma la actualización
          if (confirmacion) {
            // Actualizar el usuario
            this.miServicio.actualizarUsuario(this.indice, miUsuario).subscribe(
              () => {
                alert('Usuario actualizado correctamente');
                // Vuelve a la página de gestión
                this.volverAGestionDeUsuarios();
              },
              (error) => {
                if (error.status === 200) {
                  alert('Usuario actualizado correctamente');
                  // Vuelve a la página de gestión
                  this.volverAGestionDeUsuarios();
                } else {
                  console.error('Error al eliminar coche:', error);
                }
              }
            );
          }
        } else {
          console.error('El usuario con ID', this.indice, 'no fue encontrado.');
        }
      },
      (error) => {
        // Si ocurre un error al cargar el usuario por su ID, se captura aquí
        console.error('Error al cargar el usuario:', error);
        // Aquí puedes agregar más lógica para manejar el error, como mostrar un mensaje al usuario
      }
    );
  }

  volverAGestionDeUsuarios() {
    this.router.navigate(['gestionUsuarios']);
  }
}
