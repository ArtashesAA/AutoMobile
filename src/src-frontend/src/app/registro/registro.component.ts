import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../login/servicio/login.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css',
})
export class RegistroComponent {
  nombre_usuario: string = '';
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private router: Router, private loginService: LoginService) {}

  validacion(): boolean {
    const nombre_usuarioPattern = /^[a-zA-Z0-9._%!-]{3,20}$/;
    const emailPattern = /^[a-zA-Z0-9._-]{3,30}@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    const passwordPattern = /^[a-zA-Z0-9._%+-]{3,20}$/;

    if (!this.nombre_usuario.match(nombre_usuarioPattern)) {
      this.errorMessage =
        'Introduce un formato de nombre correcto. Puedes usar ._%!-';
      return false;
    }

    if (!this.email.match(emailPattern)) {
      this.errorMessage = 'Introduce un formato de email correcto';
      return false;
    }

    if (!this.password.match(passwordPattern)) {
      this.errorMessage =
        'La contraseña no es válida. Debe tener entre 3 y 20 caracteres.';
      return false;
    }

    return true;
  }

  register() {
    if (this.validacion()) {
      this.loginService
        .register(this.nombre_usuario, this.email, this.password)
        .subscribe(
          () => {
            // Registro exitoso, redirigir al usuario a la página de inicio de sesión
            this.router.navigate(['/registroCorrecto']);
          },
          () => {
            // Manejar errores de registro, por ejemplo, mostrar un mensaje de error al usuario
            this.errorMessage =
              'Error al registrar usuario. Por favor, inténtelo de nuevo.';
          }
        );
    }
  }
}
