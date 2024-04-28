import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css',
})
export class SidebarComponent {
  @Output() filtersApplied = new EventEmitter<any>();

  marca: string = '';
  modelo: string = '';
  categoria: string = '';
  combustible: string = '';
  anyoMin: number | null = null;
  anyoMax: number | null = null;
  precioMin: number | null = null;
  precioMax: number | null = null;
  kilometrajeMin: number | null = null;
  kilometrajeMax: number | null = null;
  potenciaMin: number | null = null;
  potenciaMax: number | null = null;
  tipo_cambio: string = '';
  plazasMin: number | null = null;
  plazasMax: number | null = null;

  aplicarFiltros(): void {
    const filters = {
      marca: this.marca,
      modelo: this.modelo,
      categoria: this.categoria,
      combustible: this.combustible,
      anyoMin: this.anyoMin,
      anyoMax: this.anyoMax,
      precioMin: this.precioMin,
      precioMax: this.precioMax,
      kilometrajeMin: this.kilometrajeMin,
      kilometrajeMax: this.kilometrajeMax,
      potenciaMin: this.potenciaMin,
      potenciaMax: this.potenciaMax,
      tipo_cambio: this.tipo_cambio,
      plazasMin: this.potenciaMin,
      plazasMax: this.potenciaMax,
    };

    this.filtersApplied.emit(filters);
  }
}
