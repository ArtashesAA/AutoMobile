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
import { DataServices } from '../DataServices';

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

  constructor(
    private router: Router,
    private miServicio: ServicioCocheService,
    private formBuilder: FormBuilder
  ) {
    this.miFormulario = this.formBuilder.group({
      marca: ['', Validators.required],
      modelo: ['', Validators.required],
      anyo: [null, [Validators.required, Validators.min(0)]],
      potencia: [null, [Validators.required, Validators.min(0)]],
      kilometraje: [null, [Validators.required, Validators.min(0)]],
      peso: [null, [Validators.required, Validators.min(0)]],
      combustible: ['', Validators.required],
      color: ['', Validators.required],
      precio: [null, [Validators.required, Validators.min(0)]],
      descripcion: ['', Validators.required],
      usuario: [null, Validators.required],
      imagenes: [[], Validators.required],
    });
  }

  coches: Coche[] = [];

  ngOnInit(): void {
    this.miServicio.obtenerCoches().subscribe((misCoches) => {
      console.log(misCoches);
      this.coches = Object.values(misCoches);

      this.miServicio.setCoches(this.coches);
    });
  }

  volverHome() {
    this.router.navigate(['']);
  }

  agregarCoche() {
    if (this.miFormulario.valid) {
      let miCoche = new Coche(
        this.miFormulario.value.marca,
        this.miFormulario.value.modelo,
        this.miFormulario.value.anyo,
        this.miFormulario.value.potencia,
        this.miFormulario.value.kilometraje,
        this.miFormulario.value.peso,
        this.miFormulario.value.combustible,
        this.miFormulario.value.color,
        this.miFormulario.value.precio,
        this.miFormulario.value.descripcion,
        this.miFormulario.value.usuario,
        this.miFormulario.value.imagenes
      );

      this.miServicio.agregarCocheServicio(miCoche);

      this.router.navigate(['/catalogo']);
    } else {
      console.log('Formulario no válido');
    }
  }

  validarFormulario(): boolean {
    return (
      this.cuadroMarca.trim() !== '' &&
      this.cuadroModelo.trim() !== '' &&
      this.cuadroAnyo > 0 &&
      this.cuadroPotencia > 0 &&
      this.cuadroKilometraje > 0 &&
      this.cuadroPeso > 0 &&
      this.cuadroCombustible.trim() !== '' &&
      this.cuadroColor.trim() !== '' &&
      this.cuadroPrecio > 0 &&
      this.cuadroDescripcion.trim() !== '' &&
      this.cuadroUsuario !== null &&
      this.cuadroImagenes.length > 0
    );
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
  cuadroUsuario!: Usuario;
  cuadroImagenes: Imagen[] = [];
}
