import { Injectable } from '@angular/core';
import { Usuario } from '../entidad/usuario.model';
import { AutenticacionService } from '../servicio-autenticacion/autenticacion.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ServicioUsuarioService {
  private token = 'userToken';
  usuarios: Usuario[] = [];

  constructor(
    private servicioAutenticacion: AutenticacionService,
    private http: HttpClient
  ) {}

  // Recupera el usuario que esta iniciado
  getUsuarioActual() {
    return this.servicioAutenticacion.cargarUsuarioActual();
  }

  // Recupera todos los usuarios
  cargarUsuarios(): Observable<Usuario[]> {
    const headers = this.servicioAutenticacion.getHeaders();

    return this.http.get<any[]>('http://localhost:8080/api/v1/admin/usuario', {
      headers,
    });
  }

  // Recupera un usuario por su id
  cargarUsuarioPorId(id: number): Observable<Usuario> {
    const headers = this.servicioAutenticacion.getHeaders();

    return this.http.get<Usuario>(
      `http://localhost:8080/api/v1/admin/usuario/${id}`,
      {
        headers,
      }
    );
  }

  // Crea un usuario
  crearUsuario(usuario: Usuario): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http.post(
      'http://localhost:8080/api/v1/public/usuario',
      usuario,
      { headers }
    );
  }

  // Actualiza un usuario por su id y los nuevos datos
  actualizarUsuario(id: number, usuario: Usuario): Observable<any> {
    const headers = this.servicioAutenticacion.getHeaders();
    const url = `http://localhost:8080/api/v1/admin/usuario/${id}`;

    console.log('Actualizando usuario ' + id);
    return this.http.put(url, usuario, {
      headers,
    });
  }

  // Actualiza un usuario por su id y los nuevos datos
  actualizarPerfil(id: number, usuario: Usuario): Observable<any> {
    const headers = this.servicioAutenticacion.getHeaders();
    const url = `http://localhost:8080/api/v1/adminuser/usuario`;

    console.log('Actualizando usuario ' + id);
    return this.http.put(url, usuario, {
      headers,
    });
  }

  // Elimina un usuario por su id
  eliminarUsuario(id: number): Observable<any> {
    const headers = this.servicioAutenticacion.getHeaders();
    const url = `http://localhost:8080/api/v1/admin/usuario/${id}`;

    console.log('Eliminando usuario ' + id);
    return this.http.delete(url, {
      headers,
    });
  }

  // --------- Gestión Token ----------------

  // Guarda el token
  guardarToken(token: string): void {
    localStorage.setItem(this.token, token);
  }

  // Recupera el token
  obtenerToken(): string | null {
    return localStorage.getItem(this.token);
  }

  // Borra el token
  eliminarToken(): void {
    localStorage.removeItem(this.token);
  }
}
