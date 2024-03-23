import { Component } from '@angular/core';
import { ServicioCochesService } from '../servicio-coches.service';
import { Coche } from '../coche.model';

@Component({
  selector: 'app-catalogo-component',
  templateUrl: './catalogo-component.component.html',
  styleUrls: ['./catalogo-component.component.css'],
})
export class CatalogoComponentComponent {
  titulo = 'Listado de Coches';

  coches: Coche[] = [];

  constructor(private miServicio: ServicioCochesService) {}
  ngOnInit(): void {
    this.miServicio.obtenerCoches().subscribe((misCoches) => {
      console.log(misCoches);
      this.coches = Object.values(misCoches);

      this.miServicio.setCoches(this.coches);
    });
  }
}
