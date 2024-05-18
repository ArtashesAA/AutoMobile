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
  modelos: string[] = [];
  marcasModelos: any = {};
  anyos: string[] = [];
  precios: string[] = [
    '1000',
    '2000',
    '3000',
    '4000',
    '5000',
    '6000',
    '7000',
    '8000',
    '9000',
    '10000',
    '15000',
    '20000',
    '25000',
    '30000',
    '35000',
    '40000',
    '45000',
    '50000',
    '75000',
    '100000',
    '150000',
    '200000',
  ];

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

  procesarCoches() {
    this.coches.forEach((coche) => {
      const marca = coche.marca;
      const modelo = coche.modelo;
      if (!this.marcasModelos[marca]) {
        this.marcasModelos[marca] = [];
      }
      this.marcasModelos[marca].push(modelo);
    });
    this.anyos = this.coches
      .map((coche) => coche.anyo.toString())
      .filter((value, index, self) => self.indexOf(value) === index)
      .sort((a, b) => parseInt(b) - parseInt(a));
  }

  obtenerMarcas(): string[] {
    return Object.keys(this.marcasModelos);
  }

  onMarcaSeleccionada() {
    this.modeloSeleccionado = '';
    this.modelos = this.marcasModelos[this.marcaSeleccionada] || [];
  }

  buscarCoches() {
    let queryParams: any = {};
    if (this.marcaSeleccionada) queryParams.marca = this.marcaSeleccionada;
    if (this.modeloSeleccionado) queryParams.modelo = this.modeloSeleccionado;
    if (this.anyoSeleccionado) queryParams.anyo = this.anyoSeleccionado;
    if (this.precioMaxSeleccionado)
      queryParams.precio = this.precioMaxSeleccionado;

    this.router.navigate(['/catalogo'], { queryParams });
  }
}
