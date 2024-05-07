import { Injectable } from '@angular/core';
import { Coche } from '../entidad/coche.model';
import { Observable, catchError, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ServicioCocheService {
  coches: Coche[] = [];
  private token = 'userToken';

  constructor(private httpClient: HttpClient) {}

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

  // Recupera todos los coches. No es necesario un token
  cargarCoches() {
    return this.httpClient.get('http://localhost:8080/api/v1/public/coche');
  }

  // Recupera un coche por su id
  cargarCochePorId(id: number): Observable<Coche> {
    const url = `http://localhost:8080/api/v1/public/coche/${id}`;
    return this.httpClient.get<Coche>(url).pipe(
      catchError((error) => {
        console.error('Error al cargar el coche:', error);
        return throwError('Error al cargar el coche');
      })
    );
  }

  // Crea un coche
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

  //Actualiza un coche por su id, y los nuevos datos del coche
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

  // Elimina un coche por su id
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
}
