import { Component, OnInit } from '@angular/core';
import { Coche } from '../entidad/coche.model';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { CommonModule } from '@angular/common';
import { DataServices } from '../servicio-general/DataServices';
import { CocheHijoComponent } from '../coche-hijo/coche-hijo.component';
import { LoginService } from '../login/servicio/login.service';

@Component({
  selector: 'app-ver-coche',
  standalone: true,
  imports: [CommonModule, RouterModule, CocheHijoComponent],
  providers: [ServicioCocheService, DataServices],
  templateUrl: './ver-coche.component.html',
  styleUrl: './ver-coche.component.css',
})
export class VerCocheComponent implements OnInit {
  coche!: Coche;
  id!: number;

  constructor(
    private route: ActivatedRoute,
    private servicioCoche: ServicioCocheService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params['id'];

      if (!isNaN(this.id)) {
        this.servicioCoche.obtenerCochePorId(this.id).subscribe(
          (coche: Coche) => {
            this.coche = coche;
          },
          (error) => {
            console.error('Error al cargar coche:', error);
          }
        );
      } else {
        console.error('Índice inválido:', this.id);
      }
    });
  }

  estaLogueado() {
    return this.loginService.estaLogueado();
  }
}
