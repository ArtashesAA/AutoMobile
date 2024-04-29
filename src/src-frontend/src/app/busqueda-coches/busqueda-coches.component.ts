import { Component } from '@angular/core';

@Component({
  selector: 'app-busqueda-coches',
  standalone: true,
  imports: [],
  templateUrl: './busqueda-coches.component.html',
  styleUrl: './busqueda-coches.component.css',
})
export class BusquedaCochesComponent {
  titulo: string = 'Búsqueda de Coches';
  coches: any[] = []; // Aquí se cargarían los coches filtrados

  aplicarFiltros(filters: any): void {
    console.log('Filtros aplicados:', filters);
  }
}
