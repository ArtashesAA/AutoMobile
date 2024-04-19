import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Coche } from '../entidad/coche.model';
import { Usuario } from '../entidad/usuario.model';
import { Observable, throwError, catchError } from 'rxjs';

@Injectable()
export class DataServices {
  constructor(private httpClient: HttpClient) {}

  private token = 'userToken';

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem(this.token);
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

  cargarCochePorId(id: number): Observable<Coche> {
    const url = `http://localhost:8080/api/v1/public/coche/${id}`;
    return this.httpClient.get<Coche>(url).pipe(
      catchError((error) => {
        console.error('Error al cargar el coche:', error);
        return throwError('Error al cargar el coche');
      })
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

  crearCoche(coche: Coche): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.httpClient
      .post('http://localhost:8080/api/v1/coche', coche, { headers })
      .pipe(
        catchError((error) => {
          return throwError('Error al crear el coche: ' + error.message);
        })
      );
  }

  actualizarCoche(id: number, coche: Coche): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/coche/${id}`;

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

  eliminarCoche(id: number): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/coche/${id}`;

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

  eliminarNoticia(id: number): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/noticia/${id}`;

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

  actualizarUsuario(id: number, usuario: Usuario): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/usuario/${id}`;

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

  eliminarUsuario(id: number): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/usuario/${id}`;

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
