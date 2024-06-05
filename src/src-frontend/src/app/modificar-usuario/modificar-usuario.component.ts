import { Component, OnInit } from '@angular/core';
import { Usuario } from '../entidad/usuario.model';
import { ServicioUsuarioService } from '../servicio-usuario/servicio-usuario.service';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-modificar-usuario',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './modificar-usuario.component.html',
  styleUrl: './modificar-usuario.component.css',
})
export class ModificarUsuarioComponent implements OnInit {
  usuario!: Usuario;
  editarContrasena: boolean = false;
  nuevaContrasena: string = '';

  constructor(
    private servicioUsuario: ServicioUsuarioService,
    private servicioAutenticacion: AutenticacionService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Cargar los datos del usuario actual
    this.servicioAutenticacion.cargarUsuarioActual().subscribe(
      (usuario: Usuario) => {
        this.usuario = usuario;
      },
      (error) => {
        console.error('Error al obtener el usuario:', error);
      }
    );
  }

  guardarCambios(): void {
    if (
      this.usuario == null ||
      (this.editarContrasena && this.nuevaContrasena == '')
    ) {
      alert(
        'Por favor, completa todos los campos correctamente antes de guardar.'
      );
      return;
    }

    if (this.usuario) {
      const usuarioActualizado: any = {
        nombre_usuario: this.usuario.nombre_usuario,
        email: this.usuario.email,
      };

      // Si la contraseña fue modificada, añadirla al objeto usuarioActualizado
      if (this.editarContrasena && this.nuevaContrasena) {
        usuarioActualizado.password = this.nuevaContrasena;
      }

      // Lógica para guardar los cambios del usuario
      this.servicioUsuario
        .actualizarPerfil(this.usuario.id, usuarioActualizado)
        .subscribe(
          () => {
            console.log('Usuario actualizado correctamente');

            if (this.editarContrasena && this.nuevaContrasena) {
              this.servicioAutenticacion.eliminarToken();
              // Realizar un nuevo login si la contraseña ha sido cambiada
              this.servicioAutenticacion
                .login(this.usuario.email, this.nuevaContrasena)
                .subscribe(
                  () => {
                    console.log('Re-autenticación exitosa');
                    this.router.navigate(['/modificarCorrecto']);
                  },
                  (error) => {
                    console.error('Error en la re-autenticación:', error);
                    alert('Error en la re-autenticación.');
                  }
                );
            } else {
              this.router.navigate(['/modificarCorrecto']);
            }
          },
          (error) => {
            if (error.status === 200) {
              console.log('Usuario actualizado correctamente');
              this.router.navigate(['/modificarCorrecto']);
            } else {
              console.error('Error al actualizar usuario:', error);
              alert('Error al actualizar usuario.');
            }
          }
        );

      this.servicioAutenticacion.eliminarToken();
    }
  }

  toggleEdicionContrasena(): void {
    this.editarContrasena = !this.editarContrasena;
    if (this.editarContrasena) {
      this.nuevaContrasena = ''; // Vaciar el campo de la nueva contraseña
    }
  }

  volver(): void {
    this.router.navigate(['perfil']);
  }
}
