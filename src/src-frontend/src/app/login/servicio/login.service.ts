import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  //Token
  private tokenKey = 'userToken';

  //Url de API para login y registro
  private url_login = 'http://localhost:8080/api/v1/auth/signin';
  private url_register = 'http://localhost:8080/api/v1/auth/signup';

  constructor(private router: Router, private http: HttpClient) {}

  //Método que realiza el login con email y contraseña (POST)
  login(email: string, password: string): Observable<any> {
    const body = { email, password };
    this.router.navigate(['/']);
    return this.http.post(this.url_login, body);
  }

  //Registro
  register(
    nombre_usuario: string,
    email: string,
    password: string
  ): Observable<any> {
    const body = { nombre_usuario, email, password };
    return this.http.post(this.url_register, body);
  }

  //Cierra sesión borrando el token
  logout() {
    localStorage.removeItem(this.tokenKey);
    this.router.navigate(['/']);
    window.location.reload();
  }
}
