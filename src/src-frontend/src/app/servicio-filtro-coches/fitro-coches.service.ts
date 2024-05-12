import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Coche } from '../entidad/coche.model';

@Injectable({
  providedIn: 'root',
})
export class FitroCochesService {
  private cochesFiltradosSubject = new BehaviorSubject<Coche[]>([]);
  cochesFiltrados$ = this.cochesFiltradosSubject.asObservable();

  constructor() {}

  actualizarCochesFiltrados(coches: Coche[]): void {
    this.cochesFiltradosSubject.next(coches);
  }
}
