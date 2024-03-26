import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Coche } from './coche.model';
import { LoginService } from './login/login.service';

@Injectable()
export class DataServices {
  constructor(
    private httpClient: HttpClient,
    private loginService: LoginService
  ) {}

  guardarCoches(coches: Coche[]) {
    this.httpClient.put('http://localhost:8080/api/v1/coche', coches).subscribe(
      (response) => console.log('Se han guardado los coches: ' + response),

      (error) => console.log('Error: ' + error)
    );
  }

  actualizarCoche(indice: number, coche: Coche) {
    let url = 'http://localhost:8080/api/v1/coche/' + indice + '.json';

    this.httpClient.put(url, coche).subscribe(
      (response) =>
        console.log('Se ha modificado correctamente el coche: ' + response),

      (error) => console.log('Error: ' + error)
    );
  }

  eliminarCoche(indice: number) {
    let url = 'http://localhost:8080/api/v1/coche/' + indice + '.json';

    this.httpClient.delete(url).subscribe(
      (response) =>
        console.log('Se ha eliminado correctamente el empleado: ' + response),

      (error) => console.log('Error: ' + error)
    );
  }

  cargarCoches() {
    const token = this.loginService.getIdToken();
    return this.httpClient.get(
      'http://localhost:8080/api/v1/coche/datos.json?auth=' + token
    );
  }
}
