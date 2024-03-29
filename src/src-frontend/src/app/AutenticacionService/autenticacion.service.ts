import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AutenticacionService {
  //Url de API
  private url = 'http://localhost:8080/api/v1/auth/signin';
  private urlCoche = 'http://localhost:8080/api/v1/coche';

  //Token
  private tokenKey = 'userToken';

  constructor(private http: HttpClient) {}

  //Método que realiza el login con email y contraseña (POST)
  login(email: string, password: string): Observable<any> {
    const body = { email, password };

    return this.http.post(this.url, body);
  }

  guardarToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  //Método para enviar el token al servidor
  enviarToken(): Observable<any> {
    const token = localStorage.getItem(this.tokenKey);

    if (token) {
      const headers = new HttpHeaders({
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json',
      });

      return this.http.get(this.urlCoche, { headers });
    } else {
      //Cambiar next-error-complete
      return throwError('Token no encontrado en localStorage');
    }
  }
}
