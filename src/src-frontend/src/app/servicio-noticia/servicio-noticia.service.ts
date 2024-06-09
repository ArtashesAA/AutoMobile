import { Injectable } from '@angular/core';
import { Noticia } from '../entidad/noticia.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ServicioNoticiaService {
  noticias: Noticia[] = [];

  private token = 'userToken';

  constructor(private httpClient: HttpClient) {}

  // Token para los urls que lo requieran
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

  // Recupera todas las noticias
  cargarNoticias(): Observable<any> {
    const headers = this.getHeaders();

    return this.httpClient.get(
      'http://localhost:8080/api/v1/adminuser/noticia',
      { headers }
    );
  }

  // Recupera una noticia por su id
  cargarNoticiaPorId(id: number): Observable<any> {
    const headers = this.getHeaders();

    return this.httpClient.get(
      `http://localhost:8080/api/v1/adminuser/noticia/${id}`,
      { headers }
    );
  }

  // Crea una noticia
  crearNoticia(noticia: Noticia): Observable<any> {
    const headers = this.getHeaders();

    return this.httpClient.post(
      'http://localhost:8080/api/v1/admin/noticia',
      noticia,
      { headers }
    );
  }

  // Actualiza una noticia por una id y los nuevos datos de la noticia
  actualizarNoticia(id: number, noticia: any): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/admin/noticia/${id}`;

    return this.httpClient.put(url, noticia, { headers });
  }

  // Elimina una noticia por su id
  eliminarNoticia(id: number): Observable<any> {
    const headers = this.getHeaders();
    const url = `http://localhost:8080/api/v1/admin/noticia/${id}`;

    return this.httpClient.delete(url, { headers });
  }
}
