import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { Coche } from '../entidad/coche.model';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CocheHijoComponent } from '../coche-hijo/coche-hijo.component';

@Component({
  selector: 'app-actualizar-coche',
  standalone: true,
  imports: [CommonModule, FormsModule, CocheHijoComponent],
  templateUrl: './actualizar-coche.component.html',
  styleUrl: './actualizar-coche.component.css',
})
export class ActualizarCocheComponent implements OnInit {
  constructor(
    private router: Router,
    private miServicio: ServicioCocheService,
    private route: ActivatedRoute
  ) {}

  coches: Coche[] = [];

  accion!: number;

  ngOnInit(): void {
    this.coches = this.miServicio.coches;
    this.indice = this.route.snapshot.params['id'];
    this.accion = parseInt(this.route.snapshot.queryParams['accion']);

    let coche: Coche = this.miServicio.encontrarCoche(this.indice);

    this.cuadroMarca = coche.modelo;
    this.cuadroModelo = coche.marca;
    this.cuadroAnyo = coche.anyo;
    this.cuadroPotencia = coche.potencia;
    this.cuadroKilometraje = coche.kilometraje;
    this.cuadroPeso = coche.peso;
    this.cuadroCombustible = coche.combustible;
    this.cuadroColor = coche.color;
    this.cuadroPrecio = coche.precio;
    this.cuadroDescripcion = coche.descripcion;
  }

  actualizaCoche() {
    if (this.accion === 1) {
      let miCoche = new Coche(
        this.cuadroMarca,
        this.cuadroModelo,
        this.cuadroAnyo,
        this.cuadroPotencia,
        this.cuadroKilometraje,
        this.cuadroPeso,
        this.cuadroCombustible,
        this.cuadroColor,
        this.cuadroPrecio,
        this.cuadroDescripcion
      );

      this.miServicio.actualizarCoche(this.indice, miCoche);
      this.router.navigate(['/catalogo']);
    } else {
      this.miServicio.eliminarCoche(this.indice);
      this.router.navigate(['/catalogo']);
    }
  }

  cuadroMarca: string = '';
  cuadroModelo: string = '';
  cuadroAnyo: number = 0;
  cuadroPotencia: number = 0;
  cuadroKilometraje: number = 0;
  cuadroPeso: number = 0;
  cuadroCombustible: string = '';
  cuadroColor: string = '';
  cuadroPrecio: number = 0;
  cuadroDescripcion: string = '';

  indice!: number;
}
