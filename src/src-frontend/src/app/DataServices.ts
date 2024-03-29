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

  cargarCoches() {
    return this.httpClient.get('http://localhost:8080/api/v1/public/coche');
  }

  cargarCochePorId(id: number) {
    return this.httpClient.get(
      'http://localhost:8080/api/v1/public/coche/' + id
    );
  }

  guardarCoches(coches: Coche[]) {
    const token = this.loginService.getIdToken();
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    });

    return this.httpClient.put('http://localhost:8080/api/v1/coche', coches, {
      headers,
    });
  }

  actualizarCoche(indice: number, coche: Coche) {
    const token = this.loginService.getIdToken();
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    });

    let url = 'http://localhost:8080/api/v1/coche/' + indice;

    return this.httpClient.put(url, coche, { headers });
  }

  eliminarCoche(indice: number) {
    const token = this.loginService.getIdToken();
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });

    let url = 'http://localhost:8080/api/v1/coche/' + indice;

    return this.httpClient.delete(url, { headers });
  }
}
