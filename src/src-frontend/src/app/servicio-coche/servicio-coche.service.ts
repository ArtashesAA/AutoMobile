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
    return this.http.get<Coche>(url);
  }

  // Recupera los coches que ha publicado un usuario
  cargarCochesPorIdUsuario(idUsuario: number): Observable<Coche[]> {
    const headers = this.getHeaders();

    const url = `http://localhost:8080/api/v1/adminuser/coche/usuario/${idUsuario}`;
    return this.http.get<Coche[]>(url, {
      headers,
    });
  }

  // Recupera los coches que tiene en favoritos un usuario
  cargarCochesFavoritosPorIdUsuario(idUsuario: number): Observable<Coche[]> {
    const headers = this.getHeaders();

    const url = `http://localhost:8080/api/v1/adminuser/coche/favorito/usuario/${idUsuario}`;
    return this.http.get<Coche[]>(url, {
      headers,
    });
  }

  // Crea un coche
  crearCoche(coche: Coche): Observable<any> {
    const headers = this.getHeaders();

    return this.http.post(
      'http://localhost:8080/api/v1/adminuser/coche',
      coche,
      {
        headers,
      }
    );
  }

  //Actualiza un coche por su id, y los nuevos datos del coche
  actualizarCoche(id: number, coche: Coche): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/admin/coche/${id}`;

    return this.http.put(url, coche, {
      headers,
    });
  }

  //Actualiza un coche por su id si este coche pertenece al usuario iniciado, y los nuevos datos del coche
  actualizarCochePropio(id: number, coche: Coche): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/adminuser/coche/${id}`;

    return this.http.put(url, coche, {
      headers,
    });
  }

  // Elimina un coche por su id
  eliminarCoche(id: number): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/admin/coche/${id}`;

    return this.http.delete(url, {
      headers,
    });
  }

  // Elimina un coche por su id si pertenece al usuario iniciado
  eliminarCochePropio(id: number): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/adminuser/coche/${id}`;

    return this.http.delete(url, {
      headers,
    });
  }
}
