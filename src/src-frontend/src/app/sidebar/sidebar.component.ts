import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Coche } from '../entidad/coche.model';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css',
})
export class SidebarComponent {
  // Recibe los filtros actuales
  @Input() filtros: any = {};

  coches: Coche[] = [];
  modelos: string[] = [];
  marcasModelos: any = {};

  marcaSeleccionada: string = '';
  modeloSeleccionado: string = '';
  anyoSeleccionado: string = '';
  precioSeleccionado: string = '';
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
  cargandoCoches: boolean = true;

  constructor(
    private servicioCoche: ServicioCocheService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.servicioCoche.cargarCoches().subscribe((misCoches) => {
      this.coches = Object.values(misCoches);
      this.procesarCoches();
      this.cargandoCoches = false;
      this.aplicarFiltrosIniciales();
    });
  }

  aplicarFiltrosIniciales() {
    // Aplicar filtros recibidos al cargar el componente
    if (this.filtros.marca) this.marcaSeleccionada = this.filtros.marca;
    if (this.filtros.modelo) this.modeloSeleccionado = this.filtros.modelo;
    if (this.filtros.anyo) this.anyoSeleccionado = this.filtros.anyo;
    if (this.filtros.precio) this.precioSeleccionado = this.filtros.precio;
    if (this.filtros.anyoMin) this.anyoMin = this.filtros.anyoMin;
    if (this.filtros.anyoMax) this.anyoMax = this.filtros.anyoMax;
    if (this.filtros.precioMin) this.precioMin = this.filtros.precioMin;
    if (this.filtros.precioMax) this.precioMax = this.filtros.precioMax;
    if (this.filtros.kilometrajeMin)
      this.kilometrajeMin = this.filtros.kilometrajeMin;
    if (this.filtros.kilometrajeMax)
      this.kilometrajeMax = this.filtros.kilometrajeMax;
    if (this.filtros.potenciaMin) this.potenciaMin = this.filtros.potenciaMin;
    if (this.filtros.potenciaMax) this.potenciaMax = this.filtros.potenciaMax;
    if (this.filtros.tipo_cambio) this.tipo_cambio = this.filtros.tipo_cambio;
    if (this.filtros.plazasMin) this.plazasMin = this.filtros.plazasMin;
    if (this.filtros.plazasMax) this.plazasMax = this.filtros.plazasMax;
  }

  procesarCoches() {
    this.coches.forEach((coche) => {
      const marca = coche.marca;
      const modelo = coche.modelo;
      if (!this.marcasModelos[marca]) {
        this.marcasModelos[marca] = [];
      }
      this.marcasModelos[marca].push(modelo);
    });
  }

  obtenerMarcas(): string[] {
    return Object.keys(this.marcasModelos);
  }

  onMarcaSeleccionada() {
    this.modeloSeleccionado = '';
    this.modelos = this.marcasModelos[this.marcaSeleccionada] || [];
  }

  aplicarFiltros() {
    let filtros: any = {};
    if (this.marcaSeleccionada) filtros.marca = this.marcaSeleccionada;
    if (this.modeloSeleccionado) filtros.modelo = this.modeloSeleccionado;
    if (this.anyoSeleccionado) filtros.anyo = this.anyoSeleccionado;
    if (this.precioSeleccionado) filtros.precio = this.precioSeleccionado;
    if (this.anyoMin) filtros.anyoMin = this.anyoMin;
    if (this.anyoMax) filtros.anyoMax = this.anyoMax;
    if (this.precioMin) filtros.precioMin = this.precioMin;
    if (this.precioMax) filtros.precioMax = this.precioMax;
    if (this.kilometrajeMin) filtros.kilometrajeMin = this.kilometrajeMin;
    if (this.kilometrajeMax) filtros.kilometrajeMax = this.kilometrajeMax;
    if (this.potenciaMin) filtros.potenciaMin = this.potenciaMin;
    if (this.potenciaMax) filtros.potenciaMax = this.potenciaMax;
    if (this.tipo_cambio) filtros.tipo_cambio = this.tipo_cambio;
    if (this.plazasMin) filtros.plazasMin = this.plazasMin;
    if (this.plazasMax) filtros.plazasMax = this.plazasMax;

    // Actualiza la URL con los nuevos filtros
    this.router.navigate([], { queryParams: filtros });
  }
}
