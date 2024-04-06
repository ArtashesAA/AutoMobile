import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../login/servicio/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css',
})
export class RegistroComponent {
  nombre_usuario: string = '';
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private router: Router, private loginService: LoginService) {}

  register() {
    this.loginService
      .register(this.nombre_usuario, this.email, this.password)
      .subscribe(
        (response) => {
          // Registro exitoso, redirigir al usuario a la página de inicio de sesión
          this.router.navigate(['/login']);
        },
        (error) => {
          // Manejar errores de registro, por ejemplo, mostrar un mensaje de error al usuario
          this.errorMessage =
            'Error al registrar usuario. Por favor, inténtelo de nuevo.';
        }
      );
  }
}
