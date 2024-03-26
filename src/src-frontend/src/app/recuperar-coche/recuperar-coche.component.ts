import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Coche } from '../coche.model';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';

@Component({
  selector: 'app-recuperar-coche',
  standalone: true,
  imports: [],
  templateUrl: './recuperar-coche.component.html',
  styleUrl: './recuperar-coche.component.css',
})
export class RecuperarCocheComponent implements OnInit {
  @Input() cocheDeLista!: Coche;
  @Input() indice!: number;

  coche!: Coche;

  constructor(
    private miServicio: ServicioCocheService,
    private router: Router
  ) {}

  coches: Coche[] = [];

  ngOnInit(): void {
    this.coche = this.miServicio.encontrarCoche(this.indice);
  }

  volverCatalogo() {
    this.router.navigate(['/catalogo']);
  }
}
