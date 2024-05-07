import { Component, OnDestroy, OnInit } from '@angular/core';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { Coche } from '../entidad/coche.model';
import { CocheHijoComponent } from '../coche-hijo/coche-hijo.component';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-catalogo-coche',
  standalone: true,
  templateUrl: './catalogo-coche.component.html',
  styleUrl: './catalogo-coche.component.css',
  imports: [CocheHijoComponent, CommonModule, SidebarComponent],
  providers: [ServicioCocheService],
})
export class CatalogoCocheComponent implements OnInit, OnDestroy {
  titulo = 'Catálogo de Coches';
  coches: Coche[] = [];
  filtrosSubscription: Subscription | undefined = undefined;

  constructor(private miServicio: ServicioCocheService) {}

  ngOnInit(): void {
    this.miServicio.cargarCoches().subscribe((misCoches) => {
      this.coches = Object.values(misCoches);
    });
  }

  ngOnDestroy(): void {
    if (this.filtrosSubscription) {
      this.filtrosSubscription.unsubscribe();
    }
  }
}
