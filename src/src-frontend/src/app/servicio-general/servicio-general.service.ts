import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ServicioGeneralService {
  router: any;
  constructor() {}

  muestraMensaje(mensaje: string) {
    alert(mensaje);
  }
}
