import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormsModule,
} from '@angular/forms';
import { Coche } from '../entidad/coche.model';
import { ServicioCocheService } from '../servicio-coche/servicio-coche.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Imagen } from '../entidad/imagen.model';
import { Usuario } from '../entidad/usuario.model';
import { DataServices } from '../servicio-general/DataServices';

@Component({
  selector: 'app-crear-coche',
  standalone: true,
  imports: [CommonModule, FormsModule],
  providers: [ServicioCocheService, DataServices],
  templateUrl: './crear-coche.component.html',
  styleUrl: './crear-coche.component.css',
})
export class CrearCocheComponent implements OnInit {
  miFormulario: FormGroup;

  coches: Coche[] = [];

  cuadroId: number | null = null;
  cuadroMarca: string = '';
  cuadroModelo: string = '';
  cuadroPrecio: number | null = null;
  cuadroAnyo: number | null = null;
  cuadroPotencia: number | null = null;
  cuadroKilometraje: number | null = null;
  cuadroCombustible: string = '';
  cuadroConsumo: string = '';
  cuadroTipo_cambio: string = '';
  cuadroCategoria: string = '';
  cuadroTipo_vehiculo: string = '';
  cuadroTraccion: string = '';
  cuadroPlazas: number | null = null;
  cuadroPuertas: number | null = null;
  cuadroGarantia: string = '';
  cuadroPeso: number | null = null;
  cuadroColor: string = '';
  cuadroNumero_marchas: number | null = null;
  cuadroNumero_cilindros: number | null = null;
  cuadroCiudad: string = '';
  cuadroDescripcion: string = '';
  cuadroUsuario: Usuario | null = null;
  cuadroImagenes: Imagen[] = [];

  constructor(
    private router: Router,
    private servicioCoche: ServicioCocheService,
    private formBuilder: FormBuilder
  ) {
    this.miFormulario = this.formBuilder.group({
      id: [null],
      marca: ['', Validators.required],
      modelo: ['', Validators.required],
      precio: [null, [Validators.required, Validators.min(0)]],
      anyo: [null, [Validators.required, Validators.min(0)]],
      potencia: [null, [Validators.required, Validators.min(0)]],
      kilometraje: [null, [Validators.required, Validators.min(0)]],
      combustible: ['', Validators.required],
      consumo: ['', Validators.required],
      tipo_cambio: ['', Validators.required],
      categoria: ['', Validators.required],
      tipo_vehiculo: ['', Validators.required],
      traccion: ['', Validators.required],
      plazas: [null, [Validators.required, Validators.min(0)]],
      puertas: [null, [Validators.required, Validators.min(0)]],
      garantia: ['', Validators.required],
      peso: [null, [Validators.required, Validators.min(0)]],
      color: ['', Validators.required],
      numero_marchas: [null, [Validators.required, Validators.min(0)]],
      numero_cilindros: [null, [Validators.required, Validators.min(0)]],
      ciudad: ['', Validators.required],
      descripcion: ['', Validators.required],
      usuario: [null, Validators.required],
      imagenes: [[]],
    });
  }

  ngOnInit(): void {}

  crearCoche() {
    if (this.miFormulario.valid) {
      const cocheData = this.miFormulario.value;

      const nuevoCoche = new Coche(
        cocheData.marca,
        cocheData.modelo,
        cocheData.precio,
        cocheData.anyo,
        cocheData.potencia,
        cocheData.kilometraje,
        cocheData.combustible,
        cocheData.consumo,
        cocheData.tipo_cambio,
        cocheData.categoria,
        cocheData.tipo_vehiculo,
        cocheData.traccion,
        cocheData.plazas,
        cocheData.puertas,
        cocheData.garantia,
        cocheData.peso,
        cocheData.color,
        cocheData.numero_marchas,
        cocheData.numero_cilindros,
        cocheData.ciudad,
        cocheData.descripcion,
        cocheData.usuario,
        cocheData.imagenes
      );

      // Hay que añadir datos del usuario
      this.servicioCoche.crearCoche(nuevoCoche);

      this.router.navigate(['/catalogo']);
    } else {
      console.log('Formulario no válido');
    }
  }

  volverAlInicio() {
    this.router.navigate(['']);
  }
}
