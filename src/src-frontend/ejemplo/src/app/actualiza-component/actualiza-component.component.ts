import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Coche } from '../coche.model';
import { ServicioCochesService } from '../servicio-coches.service';

@Component({
  selector: 'app-actualiza-component',
  templateUrl: './actualiza-component.component.html',
  styleUrls: ['./actualiza-component.component.css'],
})
export class ActualizaComponentComponent implements OnInit {
  constructor(
    private router: Router,
    private miServicio: ServicioCochesService,
    private route: ActivatedRoute
  ) {}

  coches: Coche[] = [];

  accion: number;

  ngOnInit(): void {
    this.coches = this.miServicio.coches;
    this.indice = this.route.snapshot.params['id'];
    this.accion = parseInt(this.route.snapshot.queryParams['accion']);

    let coche: Coche = this.miServicio.encontrarCoche(this.indice);

    this.cuadroMarca = coche.modelo;
    this.cuadroModelo = coche.marca;
    this.cuadroAnyo = coche.anyo;
    this.cuadroCaballos = coche.caballos;
  }

  volverHome() {
    this.router.navigate(['']);
  }

  actualizaCoche() {
    if (this.accion === 1) {
      let miCoche = new Coche(
        this.cuadroMarca,
        this.cuadroModelo,
        this.cuadroAnyo,
        this.cuadroCaballos
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
  cuadroCaballos: number = 0;

  indice: number;
}
