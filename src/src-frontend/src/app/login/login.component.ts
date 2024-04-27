import { Component } from '@angular/core';
import { LoginService } from './servicio/login.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import { Observable } from 'rxjs';
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
    private router: Router,
    public servicioLogin: LoginService
  ) {}

  onSubmit(): void {
    // Hace login con email-contraseña al enviar el formulario de login
    this.servicioAutenticacion.login(this.email, this.password).subscribe(
      (response) => {
        // Si se consigue, devuelve que se ha logueado
        console.log('Login successful.');
        window.location.reload();
      },
      (error) => {
        // Sino devuelve el mensaje de error
        console.error('Error during login:', error);
        this.errorMessage =
          'Error en la autenticación. Verifique sus credenciales.';
      }
    );
  }
}
