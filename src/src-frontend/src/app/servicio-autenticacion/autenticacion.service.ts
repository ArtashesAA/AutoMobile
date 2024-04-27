import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { Usuario } from '../entidad/usuario.model';

@Injectable({
  providedIn: 'root',
})
export class AutenticacionService {
  //Url de API
  private url = 'http://localhost:8080/api/v1/auth/signin';
  private urlUsuarioActual = 'http://localhost:8080/api/v1/usuario/actual';

  //Token
  private tokenKey = 'userToken';

  constructor(private http: HttpClient) {}

  //Método que realiza el login con email y contraseña (POST)
  login(email: string, password: string): Observable<any> {
    const body = { email, password };
    return this.http.post<any>(this.url, body).pipe(
      tap((response) => {
        if (response && response.token) {
          // Guardar el token en localStorage
          this.guardarToken(response.token);
        }
      }),
      catchError((error) => {
        return throwError('Error en la autenticación: ' + error.message);
      })
    );
  }

  guardarToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  obtenerToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  eliminarToken(): void {
    localStorage.removeItem(this.tokenKey);
  }

  cargarUsuarioActual(): Observable<Usuario> {
    const headers = this.getHeaders();

    return this.http.get<Usuario>(this.urlUsuarioActual, { headers }).pipe(
      catchError((error) => {
        console.error('Error al cargar el usuario:', error);
        return throwError('Error al cargar el usuario: ' + error.message);
      })
    );
  }

  estaAutenticado(): boolean {
    return !!this.obtenerToken();
  }

  private getHeaders(): HttpHeaders {
    const token = this.obtenerToken();
    if (token) {
      return new HttpHeaders({
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json',
      });
    }
    return new HttpHeaders({
      'Content-Type': 'application/json',
    });
  }
}
