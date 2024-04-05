import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Coche } from './entidad/coche.model';
import { LoginService } from './login/login.service';

@Injectable()
export class DataServices {
  constructor(
    private httpClient: HttpClient,
    private loginService: LoginService
  ) {}

  private getHeaders() {
    const token = this.loginService.getIdToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    });
  }

  cargarCoches() {
    return this.httpClient.get('http://localhost:8080/api/v1/public/coche');
  }

  cargarCochePorId(id: number) {
    return this.httpClient.get(
      'http://localhost:8080/api/v1/public/coche/' + id
    );
  }

  guardarCoches(coches: Coche[]) {
    return this.httpClient.put('http://localhost:8080/api/v1/coche', coches, {
      headers: this.getHeaders(),
    });
  }

  actualizarCoche(indice: number, coche: Coche) {
    let url = 'http://localhost:8080/api/v1/coche/' + indice;

    return this.httpClient.put(url, coche, { headers: this.getHeaders() });
  }

  eliminarCoche(indice: number) {
    let url = 'http://localhost:8080/api/v1/coche/' + indice;

    return this.httpClient.delete(url, { headers: this.getHeaders() });
  }

  // Noticias

  cargarNoticias() {
    return this.httpClient.get('http://localhost:8080/api/v1/noticia', {
      headers: this.getHeaders(),
    });
  }

  cargarNoticiaPorId(id: number) {
    return this.httpClient.get('http://localhost:8080/api/v1/noticia/' + id, {
      headers: this.getHeaders(),
    });
  }

  guardarNoticias(noticias: any) {
    return this.httpClient.put(
      'http://localhost:8080/api/v1/noticia',
      noticias,
      { headers: this.getHeaders() }
    );
  }

  actualizarNoticia(indice: number, noticia: any) {
    let url = 'http://localhost:8080/api/v1/noticia/' + indice;
    return this.httpClient.put(url, noticia, { headers: this.getHeaders() });
  }

  eliminarNoticia(indice: number) {
    let url = 'http://localhost:8080/api/v1/noticia/' + indice;

    return this.httpClient.delete(url, { headers: this.getHeaders() });
  }

  // Usuarios

  cargarUsuarios() {
    return this.httpClient.get('http://localhost:8080/api/v1/usuario', {
      headers: this.getHeaders(),
    });
  }

  cargarUsuarioPorId(id: number) {
    return this.httpClient.get('http://localhost:8080/api/v1/usuario/' + id, {
      headers: this.getHeaders(),
    });
  }

  guardarUsuarios(usuarios: any) {
    return this.httpClient.put(
      'http://localhost:8080/api/v1/usuario',
      usuarios,
      { headers: this.getHeaders() }
    );
  }

  actualizarUsuario(indice: number, usuario: any) {
    let url = 'http://localhost:8080/api/v1/usuario/' + indice;

    return this.httpClient.put(url, usuario, { headers: this.getHeaders() });
  }

  eliminarUsuario(indice: number) {
    let url = 'http://localhost:8080/api/v1/usuario/' + indice;

    return this.httpClient.delete(url, { headers: this.getHeaders() });
  }
}
