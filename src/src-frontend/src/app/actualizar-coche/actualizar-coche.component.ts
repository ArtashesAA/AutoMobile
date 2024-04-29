import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { Coche } from '../entidad/coche.model';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CocheHijoComponent } from '../coche-hijo/coche-hijo.component';
import { Usuario } from '../entidad/usuario.model';
import { Imagen } from '../entidad/imagen.model';
import { DataServices } from '../servicio-general/DataServices';

@Component({
  selector: 'app-actualizar-coche',
  standalone: true,
  imports: [CommonModule, FormsModule, CocheHijoComponent],
  providers: [ServicioCocheService, DataServices],
  templateUrl: './actualizar-coche.component.html',
  styleUrl: './actualizar-coche.component.css',
})
export class ActualizarCocheComponent implements OnInit {
  cuadroId: number = 0;
  cuadroMarca: string = '';
  cuadroModelo: string = '';
  cuadroPrecio: number = 0;
  cuadroAnyo: number = 0;
  cuadroPotencia: number = 0;
  cuadroKilometraje: number = 0;
  cuadroCombustible: string = '';
  cuadroConsumo: string = '';
  cuadroTipo_cambio: string = '';
  cuadroCategoria: string = '';
  cuadroTipo_vehiculo: string = '';
  cuadroTraccion: string = '';
  cuadroPlazas: number = 0;
  cuadroPuertas: number = 0;
  cuadroGarantia: string = '';
  cuadroPeso: number = 0;
  cuadroColor: string = '';
  cuadroNumero_marchas: number = 0;
  cuadroNumero_cilindros: number = 0;
  cuadroCiudad: string = '';
  cuadroDescripcion: string = '';
  cuadroUsuario: Usuario | undefined;
  cuadroImagenes: Imagen[] | undefined;

  indice!: number;
  coches: Coche[] = [];

  accion!: number;

  constructor(
    private router: Router,
    private miServicio: ServicioCocheService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.coches = this.miServicio.coches;
    this.indice = this.route.snapshot.params['id'];
    this.accion = parseInt(this.route.snapshot.queryParams['accion']);

    this.miServicio.obtenerCochePorId(this.indice).subscribe((coche: Coche) => {
      this.cuadroMarca = coche.marca;
      this.cuadroModelo = coche.modelo;
      this.cuadroPrecio = coche.precio;
      this.cuadroAnyo = coche.anyo;
      this.cuadroPotencia = coche.potencia;
      this.cuadroKilometraje = coche.kilometraje;
      this.cuadroCombustible = coche.combustible;
      this.cuadroConsumo = coche.consumo;
      this.cuadroTipo_cambio = coche.tipo_cambio;
      this.cuadroCategoria = coche.categoria;
      this.cuadroTipo_vehiculo = coche.tipo_vehiculo;
      this.cuadroTraccion = coche.traccion;
      this.cuadroPlazas = coche.plazas;
      this.cuadroPuertas = coche.puertas;
      this.cuadroGarantia = coche.garantia;
      this.cuadroPeso = coche.peso;
      this.cuadroColor = coche.color;
      this.cuadroNumero_marchas = coche.numero_marchas;
      this.cuadroNumero_cilindros = coche.numero_cilindros;
      this.cuadroCiudad = coche.ciudad;
      this.cuadroDescripcion = coche.descripcion;
      this.cuadroUsuario = coche.usuario;
      this.cuadroImagenes = coche.imagenes;
    });
  }

  actualizaCoche() {
    if (this.accion === 1) {
      const usuario: Usuario = {
        id: this.cuadroUsuario?.id || 0,
        nombre_usuario: this.cuadroUsuario?.nombre_usuario || '',
        email: this.cuadroUsuario?.email || '',
        imagen_usuario: this.cuadroUsuario?.imagen_usuario || '',
        password: this.cuadroUsuario?.password || '',
        role: this.cuadroUsuario?.role || '',
      };

      const imagenes: Imagen[] = this.cuadroImagenes || [];

      let miCoche = new Coche(
        this.cuadroMarca,
        this.cuadroModelo,
        this.cuadroPrecio,
        this.cuadroAnyo,
        this.cuadroPotencia,
        this.cuadroKilometraje,
        this.cuadroCombustible,
        this.cuadroConsumo,
        this.cuadroTipo_cambio,
        this.cuadroCategoria,
        this.cuadroTipo_vehiculo,
        this.cuadroTraccion,
        this.cuadroPlazas,
        this.cuadroPuertas,
        this.cuadroGarantia,
        this.cuadroPeso,
        this.cuadroColor,
        this.cuadroNumero_marchas,
        this.cuadroNumero_cilindros,
        this.cuadroCiudad,
        this.cuadroDescripcion,
        usuario,
        imagenes
      );

      this.miServicio.actualizarCoche(this.indice, miCoche);
      this.router.navigate(['/catalogo']);
    } else {
      this.miServicio.eliminarCoche(this.indice);
      this.router.navigate(['/catalogo']);
    }
  }

  volverACatalogo() {
    this.router.navigate(['catalogo']);
  }
}
