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
        .actualizarUsuario(this.usuario.id, usuarioActualizado)
        .subscribe(
          () => {
            console.log('Usuario actualizado correctamente');
            this.router.navigate(['/modificarCorrecto']);
          },
          (error) => {
            if (error.status === 200) {
              console.log('Usuario actualizado correctamente');
              this.router.navigate(['/modificarCorrecto']);
            } else {
              console.error('Error al actualizar usuario:', error);
            }
          }
        );
    }
  }

  toggleEdicionContrasena(): void {
    this.editarContrasena = !this.editarContrasena;
    if (this.editarContrasena) {
      this.nuevaContrasena = ''; // Vaciar el campo de la nueva contraseña
    }
  }

  volver() {
    this.router.navigate(['perfil']);
  }
}
