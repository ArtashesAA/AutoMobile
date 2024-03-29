import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private router: Router, private http: HttpClient) {}

  //Url de API
  private url_login = 'http://localhost:8080/api/v1/auth/signin';
  private url_register = 'http://localhost:8080/api/v1/auth/signin';

  //Token
  private tokenKey = 'userToken';

  //Método que realiza el login con email y contraseña (POST)
  login(email: string, password: string): Observable<any> {
    const body = { email, password };

    return this.http.post(this.url_login, body);
  }

  register(
    nombre_usuario: string,
    email: string,
    password: string
  ): Observable<any> {
    const body = { nombre_usuario, email, password };
    return this.http.post(this.url_register, body);
  }

  guardarToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  getIdToken() {
    return localStorage.getItem('token');
  }

  estaLogueado(): boolean {
    if (typeof localStorage !== 'undefined') {
      return !!localStorage.getItem('token');
    } else {
      return false;
    }
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/']);
    window.location.reload();
  }
}
