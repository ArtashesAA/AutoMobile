import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private router: Router, private httpClient: HttpClient) {}

  token!: string;

  login(email: string, password: string): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      this.httpClient
        .post<any>('http://localhost:8080/api/v1/auth/', { email, password })
        .subscribe(
          (response) => {
            this.token = response.token;
            localStorage.setItem('token', this.token);
            this.router.navigate(['/']);
            resolve();
          },
          (error) => {
            reject(error);
          }
        );
    });
  }

  getIdToken() {
    return localStorage.getItem('token');
  }

  estaLogueado() {
    return !!localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/']);
    window.location.reload();
  }
}
