import { Component, Input, OnInit } from '@angular/core';
import { Coche } from '../coche.model';
import { ActivatedRoute, Router } from '@angular/router';
import { ServicioCochesService } from '../servicio-coches.service';

@Component({
  selector: 'app-ver-coche-component',
  templateUrl: './ver-coche-component.component.html',
  styleUrls: ['./ver-coche-component.component.css'],
})
export class VerCocheComponentComponent implements OnInit {
  @Input() cocheDeLista: Coche;
  @Input() indice: number;

  coche: Coche;

  constructor(
    private miServicio: ServicioCochesService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  coches: Coche[] = [];

  ngOnInit(): void {
    this.coche = this.miServicio.encontrarCoche(this.indice);
  }

  volverCatalogo() {
    this.router.navigate(['/catalogo']);
  }
}
