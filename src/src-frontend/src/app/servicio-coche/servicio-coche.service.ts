import { Injectable } from '@angular/core';
import { Coche } from '../entidad/coche.model';
import { Observable, catchError, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ServicioCocheService {
  coches: Coche[] = [];
  private token = 'userToken';

  constructor(private http: HttpClient) {}

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
    return this.http.get('http://localhost:8080/api/v1/public/coche');
  }

  // Recupera un coche por su id
  cargarCochePorId(id: number): Observable<Coche> {
    const url = `http://localhost:8080/api/v1/public/coche/${id}`;
    return this.http.get<Coche>(url).pipe(
      catchError((error) => {
        console.error('Error al cargar el coche:', error);
        return throwError('Error al cargar el coche');
      })
    );
  }

  cochesTodosFiltros(filtros: any): Observable<Coche[]> {
    // Construye los parámetros de búsqueda
    let params = new HttpParams();
    for (let key in filtros) {
      if (filtros.hasOwnProperty(key)) {
        params = params.set(key, filtros[key]);
      }
    }

    // Realiza la solicitud HTTP al backend con los parámetros de búsqueda
    return this.http.get<Coche[]>(
      'http://localhost:8080/api/v1/public/coche/filtroTodos',
      { params }
    );
  }

  cochesPorMarca(filtros: any): Observable<Coche[]> {
    // Construye los parámetros de búsqueda
    let params = new HttpParams();
    for (let key in filtros) {
      if (filtros.hasOwnProperty(key)) {
        params = params.set(key, filtros[key]);
      }
    }

    // Realiza la solicitud HTTP al backend con los parámetros de búsqueda
    return this.http.get<Coche[]>(
      'http://localhost:8080/api/v1/public/cochesPorMarca',
      { params }
    );
  }

  // Crea un coche
  crearCoche(coche: Coche): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http
      .post('http://localhost:8080/api/v1/admin/coche', coche, { headers })
      .pipe(
        catchError((error) => {
          return throwError('Error al crear el coche: ' + error.message);
        })
      );
  }

  //Actualiza un coche por su id, y los nuevos datos del coche
  actualizarCoche(id: number, coche: Coche): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/admin/coche/${id}`;

    return this.http
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
    const url = `http://localhost:8080/api/v1/admin/coche/${id}`;

    console.log('Eliminando coche ' + id);
    return this.http
      .delete(url, {
        headers,
      })
      .pipe(
        catchError((error) => {
          return throwError('Error al eliminar coche: ' + error);
        })
      );
  }
}
