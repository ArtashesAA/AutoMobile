import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ServicioGeneralService {
  constructor() {}

  muestraMensaje(mensaje: string) {
    alert(mensaje);
  }
}
