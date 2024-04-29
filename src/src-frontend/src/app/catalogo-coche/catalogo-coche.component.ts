import { Component, OnDestroy, OnInit } from '@angular/core';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { Coche } from '../entidad/coche.model';
import { CocheHijoComponent } from '../coche-hijo/coche-hijo.component';
import { DataServices } from '../servicio-general/DataServices';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { Subscription } from 'rxjs';
import { FiltroService } from '../servicio-filtro/filtro.service';

@Component({
  selector: 'app-catalogo-coche',
  standalone: true,
  templateUrl: './catalogo-coche.component.html',
  styleUrl: './catalogo-coche.component.css',
  imports: [CocheHijoComponent, CommonModule, SidebarComponent],
  providers: [ServicioCocheService, DataServices],
})
export class CatalogoCocheComponent implements OnInit, OnDestroy {
  titulo = 'Catálogo de Coches';
  coches: Coche[] = [];
  filtrosSubscription: Subscription | undefined = undefined;

  constructor(
    private miServicio: ServicioCocheService,
    private filtrosService: FiltroService
  ) {}

  ngOnInit(): void {
    this.miServicio.obtenerCoches().subscribe((misCoches) => {
      console.log(misCoches);
      this.coches = Object.values(misCoches);
      this.miServicio.guardarCoches(this.coches);
    });

    this.filtrosSubscription = this.filtrosService.filtrosAplicados$.subscribe(
      (filtros) => {
        console.log('Filtros aplicados:', filtros);
      }
    );
  }

  ngOnDestroy(): void {
    if (this.filtrosSubscription) {
      this.filtrosSubscription.unsubscribe();
    }
  }
}
