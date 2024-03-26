import { Component } from '@angular/core';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { Coche } from '../coche.model';
import { CocheHijoComponent } from '../coche-hijo/coche-hijo.component';

@Component({
  selector: 'app-catalogo-coche',
  standalone: true,
  templateUrl: './catalogo-coche.component.html',
  styleUrl: './catalogo-coche.component.css',
  imports: [CocheHijoComponent],
})
export class CatalogoCocheComponent {
  titulo = 'Catálogo de Coches';

  coches: Coche[] = [];

  constructor(private miServicio: ServicioCocheService) {}
  ngOnInit(): void {
    this.miServicio.obtenerCoches().subscribe((misCoches) => {
      console.log(misCoches);
      this.coches = Object.values(misCoches);

      this.miServicio.setCoches(this.coches);
    });
  }
}
