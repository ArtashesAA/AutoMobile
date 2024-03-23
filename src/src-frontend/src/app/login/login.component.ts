import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
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
