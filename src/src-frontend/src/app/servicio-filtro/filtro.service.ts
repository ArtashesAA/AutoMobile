import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FiltroService {
  private filtrosAplicadosSubject = new Subject<any>();

  filtrosAplicados$ = this.filtrosAplicadosSubject.asObservable();

  emitirFiltros(filtros: any): void {
    this.filtrosAplicadosSubject.next(filtros);
  }
}
