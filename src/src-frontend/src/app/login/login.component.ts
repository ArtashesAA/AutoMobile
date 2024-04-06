import { Component, OnInit } from '@angular/core';
import { LoginService } from './servicio/login.service';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';

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
  token: string = '';
  cochesResponse: any;
  errorMessage: string = '';

  constructor(public loginService: LoginService, private router: Router) {}

  login(): void {
    this.loginService.login(this.email, this.password).subscribe((response) => {
      const token = response.token;
      this.loginService.guardarToken(token);
    });
  }
}
