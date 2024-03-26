import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
  constructor(private loginService: LoginService) {}

  ngOnInit(): void {}

  errorMessage: string = '';

  login(form: NgForm) {
    const email = form.value.email;
    const password = form.value.password;

    this.loginService.login(email, password).catch((error) => {
      console.error('Login error:', error);

      this.errorMessage = 'Usuario o contraseña incorrectos';
    });
  }
}
