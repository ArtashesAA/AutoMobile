import { Component, OnInit } from '@angular/core';
import { Coche } from '../entidad/coche.model';
import { ActivatedRoute } from '@angular/router';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { CommonModule } from '@angular/common';
import { DataServices } from '../servicio-general/DataServices';

@Component({
  selector: 'app-ver-coche',
  standalone: true,
  imports: [CommonModule],
  providers: [ServicioCocheService, DataServices],
  templateUrl: './ver-coche.component.html',
  styleUrl: './ver-coche.component.css',
})
export class VerCocheComponent implements OnInit {
  coche!: Coche;

  constructor(
    private route: ActivatedRoute,
    private servicioCoche: DataServices
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      const idParam = params['id'];
      const id = parseInt(idParam, 10);

      if (!isNaN(id)) {
        this.servicioCoche.cargarCochePorId(idParam).subscribe(
          (coche: Coche) => {
            this.coche = coche;
          },
          (error) => {
            console.error('Error al cargar coche:', error);
          }
        );
      } else {
        console.error('Índice inválido:', idParam);
      }
    });
  }
}
