import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { Coche } from '../entidad/coche.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule],
  providers: [ServicioCocheService],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css',
})
export class InicioComponent implements OnInit {
  coches: Coche[] = [];
  cochesFiltrados: Coche[] = [];

  modelos: String[] = [];
  marcasModelos: any = {};

  marcaSeleccionada: string = '';
  modeloSeleccionado: string = '';
  anyoSeleccionado: string = '';
  precioMaxSeleccionado: string = '';
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
    });
  }

  // Procesa los coches
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

  // Guarda las marcas obtenidas
  obtenerMarcas(): string[] {
    return Object.keys(this.marcasModelos);
  }

  // Devuelve modelos según la marca seleccionada
  onMarcaSeleccionada() {
    // Restablece el modelo seleccionado al cambiar la marca
    this.modeloSeleccionado = '';
    this.modelos = this.marcasModelos[this.marcaSeleccionada] || [];
  }

  // Método que hace la búsqueda
  buscarCoches() {
    // Construye la URL con los parámetros de los filtros seleccionados
    let queryParams: any = {};
    if (this.marcaSeleccionada) queryParams.marca = this.marcaSeleccionada;
    if (this.modeloSeleccionado) queryParams.modelo = this.modeloSeleccionado;
    if (this.anyoSeleccionado) queryParams.anyo = this.anyoSeleccionado;
    if (this.precioMaxSeleccionado)
      queryParams.precio = this.precioMaxSeleccionado;

    // Navega a la página del catálogo con los filtros aplicados como parámetros de consulta
    this.router.navigate(['/catalogo'], { queryParams });
  }

  filtrarCochesPorMarca(marca: string): void {
    // Construye los parámetros de búsqueda
    const queryParams = { marca: marca };

    // Realiza la solicitud HTTP para obtener coches filtrados por marca
    this.servicioCoche
      .cochesPorMarca(queryParams)
      .subscribe((cochesFiltrados) => {
        // Actualiza la lista de coches filtrados
        this.cochesFiltrados = cochesFiltrados;

        // Actualiza los coches filtrados en el servicio de filtro
        console.log(this.coches);
      });
  }
}
