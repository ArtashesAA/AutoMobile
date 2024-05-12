import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  Observable,
  Subscription,
  catchError,
  map,
  tap,
  throwError,
} from 'rxjs';
import { Usuario } from '../entidad/usuario.model';

@Injectable({
  providedIn: 'root',
})
export class AutenticacionService {
  //Token
  private token = 'userToken';
  usuario: Usuario | undefined;

  //Urls de API
  private url = 'http://localhost:8080/api/v1/auth/signin';
  private urlUsuarioActual =
    'http://localhost:8080/api/v1/adminuser/usuario/actual';

  constructor(private http: HttpClient) {}

  // Token para los urls que lo requieran
  public getHeaders(): HttpHeaders {
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

  // Guarda el token
  guardarToken(token: string): void {
    localStorage.setItem(this.token, token);
  }

  // Recupera el token
  obtenerToken(): string | null {
    if (typeof localStorage !== 'undefined') {
      return localStorage.getItem(this.token);
    }
    return null;
  }

  // Borra el token
  eliminarToken(): void {
    localStorage.removeItem(this.token);
  }

  // Recupera el usuario actual
  cargarUsuarioActual(): Observable<Usuario> {
    const headers = this.getHeaders();

    return this.http.get<Usuario>(this.urlUsuarioActual, { headers }).pipe(
      catchError((error) => {
        console.error('Error al cargar el usuario:', error);
        return throwError('Error al cargar el usuario: ' + error.message);
      })
    );
  }

  // Comprueba si esta logueado a partir del token
  estaAutenticado(): boolean {
    try {
      const token = this.obtenerToken();
      if (!token) {
        return false; // No hay token, el usuario no está autenticado
      }
      // Realiza cualquier validación adicional del token aquí, si es necesario
      return true; // El usuario está autenticado
    } catch (error) {
      console.error('Error al verificar la autenticación:', error);
      return false; // Maneja cualquier error y devuelve false
    }
  }

  esAdmin(): Observable<boolean> {
    return this.cargarUsuarioActual().pipe(
      map((usuario: Usuario) => {
        return usuario.role === 'ADMIN';
      }),
      catchError((error) => {
        console.error('Error al cargar el usuario:', error);
        return throwError('Error al cargar el usuario: ' + error.message);
      })
    );
  }
}
