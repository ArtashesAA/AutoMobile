import { Component, OnInit } from '@angular/core';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { Coche } from '../entidad/coche.model';
import { CocheHijoComponent } from '../coche-hijo/coche-hijo.component';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-catalogo-coche',
  standalone: true,
  templateUrl: './catalogo-coche.component.html',
  styleUrl: './catalogo-coche.component.css',
  imports: [CocheHijoComponent, CommonModule, SidebarComponent],
  providers: [ServicioCocheService],
})
export class CatalogoCocheComponent implements OnInit {
  // Almacenan los datos de los coches
  coches: Coche[] = [];
  cochesFiltrados: Coche[] = [];

  // Página de Paginación
  paginaActual: number = 1;

  // Almacena los filtros del inicio para mostrarlos luego en el sidebar del catálogo
  filtros: any = {};

  constructor(
    private miServicio: ServicioCocheService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Carga todos los coches
    this.miServicio.cargarCoches().subscribe((misCoches) => {
      this.coches = Object.values(misCoches);
      this.route.queryParams.subscribe((params) => {
        // Guarda los filtros en el caso de que los eligamos al inicio
        this.filtros = params;

        // Filtra los coches
        this.filtrarCoches(params);
      });
    });
  }

  // Filtra los coches con params
  filtrarCoches(params: any): void {
    this.cochesFiltrados = this.coches.filter((coche) => {
      return (
        (!params.marca || coche.marca === params.marca) &&
        (!params.modelo || coche.modelo === params.modelo) &&
        (!params.anyo || coche.anyo.toString() === params.anyo) &&
        (!params.precio || coche.precio <= +params.precio) &&
        (!params.anyoMin || coche.anyo >= +params.anyoMin) &&
        (!params.anyoMax || coche.anyo <= +params.anyoMax) &&
        (!params.precioMin || coche.precio >= +params.precioMin) &&
        (!params.precioMax || coche.precio <= +params.precioMax) &&
        (!params.kilometrajeMin ||
          coche.kilometraje >= +params.kilometrajeMin) &&
        (!params.kilometrajeMax ||
          coche.kilometraje <= +params.kilometrajeMax) &&
        (!params.potenciaMin || coche.potencia >= +params.potenciaMin) &&
        (!params.potenciaMax || coche.potencia <= +params.potenciaMax) &&
        (!params.tipo_cambio || coche.tipoCambio === params.tipoCambio) &&
        (!params.plazasMin || coche.plazas >= +params.plazasMin) &&
        (!params.plazasMax || coche.plazas <= +params.plazasMax)
      );
    });
  }

  // Ordena los coches según el criterio seleccionado
  ordenarCoches(event: any): void {
    const criterio = event.target.value;

    if (criterio === 'precio-asc') {
      this.cochesFiltrados.sort((a, b) => a.precio - b.precio);
    } else if (criterio === 'precio-desc') {
      this.cochesFiltrados.sort((a, b) => b.precio - a.precio);
    } else if (criterio === 'kilometraje-asc') {
      this.cochesFiltrados.sort((a, b) => a.kilometraje - b.kilometraje);
    } else if (criterio === 'kilometraje-desc') {
      this.cochesFiltrados.sort((a, b) => b.kilometraje - a.kilometraje);
    }
  }

  // Paginación
  get cochesPaginados(): any[] {
    const primero = (this.paginaActual - 1) * 20;
    const ultimo = Math.min(primero + 10, this.cochesFiltrados.length);
    return this.cochesFiltrados.slice(primero, ultimo);
  }

  // Para a la página anterior
  anteriorPag() {
    this.paginaActual--;
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  // Para a la siguiente página
  siguientePag() {
    this.paginaActual++;
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  // Obtiene el total de paginas dependiendo de los coches que se mostrarán en la página
  get totalPaginas(): number {
    // En este caso serían 20 coches por pag.
    return Math.ceil(this.cochesFiltrados.length / 20);
  }

  volverAInicio() {
    this.router.navigate(['']);
  }
}
