import { Component, OnInit } from '@angular/core';
import { Coche } from '../entidad/coche.model';
import { ActivatedRoute } from '@angular/router';
import { DataServices } from '../DataServices';
import { CommonModule } from '@angular/common';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';

@Component({
  selector: 'app-ver-coche',
  standalone: true,
  imports: [CommonModule],
  providers: [ServicioCocheService, DataServices],
  templateUrl: './ver-coche.component.html',
  styleUrl: './ver-coche.component.css',
})
export class VerCocheComponent implements OnInit {
  cocheId!: number;
  coche!: Coche;

  constructor(
    private route: ActivatedRoute,
    private cocheService: ServicioCocheService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.cocheId = +params['id'] + 1; // Obtener el ID del coche de los parámetros de la ruta
      this.cargarCoche();
    });
  }

  cargarCoche(): void {
    this.cocheService.getCoche(this.cocheId).subscribe((coche: Coche) => {
      this.coche = coche;
    });
  }
}
