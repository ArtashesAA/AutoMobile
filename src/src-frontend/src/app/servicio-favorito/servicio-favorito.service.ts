import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Favorito } from '../entidad/favorito.model';
import { Observable, catchError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ServicioFavoritoService {
  favoritos: Favorito[] = [];
  private token = 'userToken';

  private baseUrl = 'http://localhost:8080/api/v1/adminuser/favorito';

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

  // Recupera todos los favoritos
  cargarFavoritos(): Observable<Favorito[]> {
    const headers = this.getHeaders();
    return this.http.get<Favorito[]>(this.baseUrl, { headers }).pipe(
      catchError((error) => {
        throw error;
      })
    );
  }

  // Recupera un favorito por su id
  cargarFavoritoPorId(id: number): Observable<Favorito> {
    const headers = this.getHeaders();
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Favorito>(url, { headers }).pipe(
      catchError((error) => {
        throw error;
      })
    );
  }

  // Recupera los favoritos por id del usuario
  cargarFavoritosPorIdUsuario(idUsuario: number): Observable<Favorito[]> {
    const headers = this.getHeaders();
    const url = `${this.baseUrl}/usuario/${idUsuario}`;
    return this.http.get<Favorito[]>(url, { headers });
  }

  // Crea un favorito
  crearFavorito(favoritoData: any): Observable<Favorito> {
    const headers = this.getHeaders();
    return this.http
      .post<Favorito>(this.baseUrl, favoritoData, { headers })
      .pipe(
        catchError((error) => {
          throw error;
        })
      );
  }

  // Elimina un favorito por su id
  eliminarFavorito(id: number): Observable<any> {
    const headers = this.getHeaders();
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete(url, { headers }).pipe(
      catchError((error) => {
        throw error;
      })
    );
  }
}
