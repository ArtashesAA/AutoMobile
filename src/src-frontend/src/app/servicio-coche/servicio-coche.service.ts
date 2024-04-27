import { Injectable } from '@angular/core';
import { Coche } from '../entidad/coche.model';
import { ServicioGeneralService } from '../servicio-general/servicio-general.service';
import { DataServices } from '../servicio-general/DataServices';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ServicioCocheService {
  coches: Coche[] = [];

  constructor(
    private servicioGeneral: ServicioGeneralService,
    private dataService: DataServices
  ) {}

  // Recibe un coche por id
  obtenerCochePorId(id: number): Observable<Coche> {
    return this.dataService.cargarCochePorId(id);
  }

  // Recibe todos los coches de la base de datos
  obtenerCoches() {
    return this.dataService.cargarCoches();
  }

  // Guarda los coches
  guardarCoches(misCoches: Coche[]) {
    this.coches = misCoches;
  }

  // Crea un coche nuevo
  crearCoche(coche: Coche) {
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

  // Actualiza un coche por id y los datos del nuevo coche
  actualizarCoche(id: number, coche: Coche) {
    let cocheModificado = this.coches[id];

    cocheModificado.marca = coche.marca;
    cocheModificado.modelo = coche.modelo;
    cocheModificado.precio = coche.precio;
    cocheModificado.anyo = coche.anyo;
    cocheModificado.potencia = coche.potencia;
    cocheModificado.kilometraje = coche.kilometraje;
    cocheModificado.combustible = coche.combustible;
    cocheModificado.consumo = coche.consumo;
    cocheModificado.tipo_cambio = coche.tipo_cambio;
    cocheModificado.categoria = coche.categoria;
    cocheModificado.tipo_vehiculo = coche.tipo_vehiculo;
    cocheModificado.traccion = coche.traccion;
    cocheModificado.plazas = coche.plazas;
    cocheModificado.puertas = coche.puertas;
    cocheModificado.garantia = coche.garantia;
    cocheModificado.peso = coche.peso;
    cocheModificado.color = coche.color;
    cocheModificado.numero_marchas = coche.numero_marchas;
    cocheModificado.numero_cilindros = coche.numero_cilindros;
    cocheModificado.ciudad = coche.ciudad;
    cocheModificado.descripcion = coche.descripcion;
    cocheModificado.usuario = coche.usuario;
    cocheModificado.imagenes = coche.imagenes;

    this.dataService.actualizarCoche(id, coche);
  }

  // Elimina un coche por id
  eliminarCoche(id: number) {
    this.servicioGeneral.muestraMensaje('Coche eliminado');
    this.coches.splice(id, 1);
    // Elimina el coche
    this.dataService.eliminarCoche(id);

    // Por último guarda los coches
    if (this.coches != null) this.dataService.guardarCoches(this.coches);
  }
}
