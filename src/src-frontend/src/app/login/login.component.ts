import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  //Variables
  email: string = '';
  password: string = '';
  token: string = '';
  cochesResponse: any;
  errorMessage: string = '';

  constructor(private loginService: LoginService) {}

  //Realiza el login llamando al método login del servicio de autenticación
  login() {
    this.loginService.login(this.email, this.password).subscribe((response) => {
      this.token = response.token;
      console.log(this.token);
      // Almacena el token en localStorage después de obtenerlo
      this.loginService.guardarToken(this.token);
    });
  }
}
