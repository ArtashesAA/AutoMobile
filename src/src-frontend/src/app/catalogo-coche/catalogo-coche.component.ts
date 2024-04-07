import { Component } from '@angular/core';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { Coche } from '../entidad/coche.model';
import { CocheHijoComponent } from '../coche-hijo/coche-hijo.component';
import { DataServices } from '../servicio-general/DataServices';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-catalogo-coche',
  standalone: true,
  templateUrl: './catalogo-coche.component.html',
  styleUrl: './catalogo-coche.component.css',
  imports: [CocheHijoComponent, CommonModule],
  providers: [ServicioCocheService, DataServices],
})
export class CatalogoCocheComponent {
  titulo = 'Catálogo de Coches';

  coches: Coche[] = [];

  constructor(private miServicio: ServicioCocheService) {}

  ngOnInit(): void {
    this.miServicio.obtenerCoches().subscribe((misCoches) => {
      console.log(misCoches);
      this.coches = Object.values(misCoches);

      this.miServicio.guardarCoches(this.coches);
    });
  }
}
