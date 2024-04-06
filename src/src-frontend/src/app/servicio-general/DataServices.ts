import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Coche } from '../entidad/coche.model';
import { LoginService } from '../login/servicio/login.service';
import { Usuario } from '../entidad/usuario.model';
import { Observable, throwError, catchError } from 'rxjs';
import { Noticia } from '../entidad/noticia.model';

@Injectable()
export class DataServices {
  constructor(
    private httpClient: HttpClient,
    private loginService: LoginService
  ) {}

  private tokenKey = 'userToken';

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem(this.tokenKey);
    if (token) {
      return new HttpHeaders({
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json',
      });
    } else {
      throw new Error('Token no encontrado en localStorage');
    }
  }

  cargarCoches() {
    return this.httpClient.get('http://localhost:8080/api/v1/public/coche');
  }

  cargarCochePorId(id: number) {
    return this.httpClient.get(
      'http://localhost:8080/api/v1/public/coche/' + id
    );
  }

  guardarCoches(coches: Coche[]): Observable<any> {
    const headers = this.getHeaders();

    return this.httpClient
      .put('http://localhost:8080/api/v1/coche', coches, {
        headers,
      })
      .pipe(
        catchError((error) => {
          return throwError('Error al guardar coches: ' + error.message);
        })
      );
  }

  actualizarCoche(indice: number, coche: Coche): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/coche/${indice}`;

    return this.httpClient
      .put(url, coche, {
        headers,
      })
      .pipe(
        catchError((error) => {
          return throwError('Error al actualizar coche: ' + error.message);
        })
      );
  }

  eliminarCoche(indice: number): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/coche/${indice}`;

    return this.httpClient
      .delete(url, {
        headers,
      })
      .pipe(
        catchError((error) => {
          return throwError('Error al eliminar coche: ' + error.message);
        })
      );
  }

  // Noticias

  cargarNoticias(): Observable<any> {
    const headers = this.getHeaders();

    return this.httpClient
      .get('http://localhost:8080/api/v1/noticia', { headers })
      .pipe(
        catchError((error) => {
          return throwError('Error al cargar noticias: ' + error.message);
        })
      );
  }

  cargarNoticiaPorId(id: number): Observable<any> {
    const headers = this.getHeaders();

    return this.httpClient
      .get(`http://localhost:8080/api/v1/noticia/${id}`, { headers })
      .pipe(
        catchError((error) => {
          return throwError('Error al cargar la noticia: ' + error.message);
        })
      );
  }

  guardarNoticias(noticias: any): Observable<any> {
    const headers = this.getHeaders();

    return this.httpClient
      .put('http://localhost:8080/api/v1/noticia', noticias, { headers })
      .pipe(
        catchError((error) => {
          return throwError('Error al guardar las noticias: ' + error.message);
        })
      );
  }

  actualizarNoticia(indice: number, noticia: any): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/noticia/${indice}`;

    return this.httpClient.put(url, noticia, { headers }).pipe(
      catchError((error) => {
        return throwError('Error al actualizar la noticia: ' + error.message);
      })
    );
  }

  eliminarNoticia(indice: number): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/noticia/${indice}`;

    return this.httpClient.delete(url, { headers }).pipe(
      catchError((error) => {
        return throwError('Error al eliminar la noticia: ' + error.message);
      })
    );
  }

  // Usuarios

  cargarUsuarios(): Observable<Usuario[]> {
    const headers = this.getHeaders();

    return this.httpClient
      .get<Usuario[]>('http://localhost:8080/api/v1/usuario', {
        headers,
      })
      .pipe(
        catchError((error) => {
          return throwError('Error al cargar usuarios: ' + error.message);
        })
      );
  }

  cargarUsuarioPorId(id: number): Observable<Usuario> {
    const headers = this.getHeaders();

    return this.httpClient
      .get<Usuario>(`http://localhost:8080/api/v1/usuario/${id}`, {
        headers,
      })
      .pipe(
        catchError((error) => {
          return throwError('Error al cargar el usuario: ' + error.message);
        })
      );
  }

  guardarUsuarios(usuarios: Usuario[]): Observable<any> {
    const headers = this.getHeaders();

    return this.httpClient
      .put('http://localhost:8080/api/v1/usuario', usuarios, {
        headers,
      })
      .pipe(
        catchError((error) => {
          return throwError('Error al guardar usuarios: ' + error.message);
        })
      );
  }

  actualizarUsuario(indice: number, usuario: Usuario): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/usuario/${indice}`;

    return this.httpClient
      .put(url, usuario, {
        headers,
      })
      .pipe(
        catchError((error) => {
          return throwError('Error al actualizar usuario: ' + error.message);
        })
      );
  }

  eliminarUsuario(indice: number): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/usuario/${indice}`;

    return this.httpClient
      .delete(url, {
        headers,
      })
      .pipe(
        catchError((error) => {
          return throwError('Error al eliminar usuario: ' + error.message);
        })
      );
  }
}
