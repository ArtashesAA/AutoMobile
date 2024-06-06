import { Component } from '@angular/core';
import { LoginService } from './servicio/login.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, RouterModule],
  providers: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  //Variables
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(
    private servicioAutenticacion: AutenticacionService,
    public servicioLogin: LoginService
  ) {}

  validacion(): boolean {
    const emailPattern = /^[a-zA-Z0-9._-]{3,30}@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    const passwordPattern = /^[a-zA-Z0-9._%!-]{3,20}$/;

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

  onSubmit(): void {
    // Verifica la validez de los inputs antes de intentar el login
    if (this.validacion()) {
      // Hace login con email-contraseña al enviar el formulario de login
      this.servicioAutenticacion.login(this.email, this.password).subscribe(
        () => {
          // Si se consigue, devuelve que se ha logueado
          console.log('Login successful.');
          window.location.reload();
        },
        (error) => {
          // Sino devuelve el mensaje de error
          console.error('Error during login:', error);
          this.errorMessage =
            'Email o contraseña incorrectos. Verifique sus credenciales.';
        }
      );
    }
  }

  estaLogueado() {
    return this.servicioAutenticacion.estaAutenticado();
  }
}
