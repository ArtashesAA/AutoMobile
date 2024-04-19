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
  precio: number | null = null;
  anyo: number | null = null;

  aplicarFiltros(): void {
    const filters = {
      marca: this.marca,
      modelo: this.modelo,
      precio: this.precio,
      anyo: this.anyo,
    };

    this.filtersApplied.emit(filters);
  }
}
