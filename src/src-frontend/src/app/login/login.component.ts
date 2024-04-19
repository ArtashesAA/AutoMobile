import { Component } from '@angular/core';
import { LoginService } from './servicio/login.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AutenticacionService } from '../AutenticacionService/autenticacion.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
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
    this.servicioAutenticacion.login(this.email, this.password).subscribe(
      (response) => {
        console.log('Login successful.');
        // Optionally, perform additional actions upon successful login
        this.router.navigate(['/perfil']);
      },
      (error) => {
        console.error('Error during login:', error);
        this.errorMessage =
          'Error en la autenticación. Verifique sus credenciales.';
      }
    );
  }
}
