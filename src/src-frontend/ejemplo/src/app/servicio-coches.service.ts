import { Injectable } from '@angular/core';
import { Coche } from './coche.model';
import { ServicioGeneralService } from './servicio-general.service';
import { DataServices } from './data.services';

@Injectable({
  providedIn: 'root',
})
export class ServicioCochesService {
  constructor(
    private servicioGeneral: ServicioGeneralService,
    private dataService: DataServices
  ) {}

  setCoches(misCoches: Coche[]) {
    this.coches = misCoches;
  }

  obtenerCoches() {
    return this.dataService.cargarCoches();
  }

  coches: Coche[] = [];

  agregarCocheServicio(coche: Coche) {
    this.servicioGeneral.muestraMensaje(
      'Coche que se va a agregar: ' + '\n' + coche.marca + ' ' + coche.modelo
    );
    this.coches.push(coche);
    if (this.coches.length === 1) {
      this.dataService.guardarCoches(this.coches);
    } else {
      this.dataService.guardarCoches(this.coches);
    }
  }

  actualizarCoche(indice: number, coche: Coche) {
    let cocheModificado = this.coches[indice];

    cocheModificado.marca = coche.marca;
    cocheModificado.modelo = coche.modelo;
    cocheModificado.anyo = coche.anyo;
    cocheModificado.caballos = coche.caballos;

    this.dataService.actualizarCoche(indice, coche);
  }

  eliminarCoche(indice: number) {
    this.servicioGeneral.muestraMensaje('Coche eliminado');
    this.coches.splice(indice, 1);
    this.dataService.eliminarCoche(indice);

    if (this.coches != null) this.dataService.guardarCoches(this.coches);
  }

  encontrarCoche(indice: number) {
    let coche: Coche = this.coches[indice];
    return coche;
  }
}
